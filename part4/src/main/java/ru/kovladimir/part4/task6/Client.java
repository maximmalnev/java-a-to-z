package ru.kovladimir.part4.task6;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Client.
 */
public class Client {

    /**
     * Inet Address to server.
     */
    private InetAddress serverAddress;

    /**
     * Server port.
     */
    private int serverPort;

    /**
     * Path to file to write log.
     */
    private final String LOG_FILE;

    /**
     * Socket to server.
     */
    private Socket socket;

    /**
     * Stream from server.
     */
    private DataInputStream inputStreamFromServer;

    /**
     * Stream to server.
     */
    private DataOutputStream outputStreamToServer;

    /**
     * Pause flag.
     */
    private boolean pause = false;

    /**
     * Exit flag.
     */
    private boolean exit = false;

    /**
     * Stream to print log.
     */
    private PrintStream logStream;

    /**
     * Stream to scan string from user.
     */
    private Scanner scannerFromUser;

    /**
     * Start word.
     */
    private final String START = "продолжить";

    /**
     * Pause word.
     */
    private final String PAUSE = "стоп";

    /**
     * Exit word.
     */
    private final String EXIT = "закончить";

    /**
     * Constructor.
     * @param serverIP String.
     * @param serverPort int.
     * @param LOG_FILE String.
     * @throws UnknownHostException
     */
    public Client(String serverIP, int serverPort, String LOG_FILE) throws UnknownHostException {
        this.serverAddress = InetAddress.getByName(serverIP);
        this.serverPort = serverPort;
        this.LOG_FILE = LOG_FILE;
    }

    /**
     * Start Client. Main loop.
     * @throws IOException
     */
    public void start() throws IOException {
        init();
        while (!exit) {
            String messageFromClient = getMessageFromClient();
            logMessage(messageFromClient);
            checkCommands(messageFromClient);
            if (!exit && !pause) {
                sendMessageToServer(messageFromClient);
                String response = getResponseFromServer();
                printMessageToClient(response);
                logMessage(response);
            }
        }
        close();
    }

    /**
     * Close connection.
     * @throws IOException
     */
    public void close() throws IOException {
        socket.close();
    }

    /**
     * Init.
     * @throws IOException
     */
    private void init() throws IOException {
        logStream = new PrintStream(new FileOutputStream(LOG_FILE));
        scannerFromUser = new Scanner(System.in);
        socket = createSocket(serverAddress, serverPort);
        inputStreamFromServer = new DataInputStream(socket.getInputStream());
        outputStreamToServer = new DataOutputStream(socket.getOutputStream());
    }

    /**
     * Create socket.
     * @param inetAddress InetAddress.
     * @param serverPort int.
     * @return Socket.
     * @throws IOException
     */
    protected Socket createSocket(InetAddress inetAddress, int serverPort) throws IOException {
        return new Socket(inetAddress, serverPort);
    }

    /**
     * Get message from client.
     * @return String.
     */
    private String getMessageFromClient() {
        return scannerFromUser.nextLine();
    }

    /**
     * Check flags.
     * @param message String.
     */
    private void checkCommands(String message) {
        if (PAUSE.equals(message)) {
            pause = true;
        } else if (START.equals(message)) {
            pause = false;
        } else if (EXIT.equals(message)) {
            exit = true;
        }
    }

    /**
     * Get response from server.
     * @return String.
     * @throws IOException
     */
    private String getResponseFromServer() throws IOException {
        return inputStreamFromServer.readUTF();
    }

    /**
     * Write message to log-file.
     * @param message String.
     */
    private void logMessage(String message) {
        logStream.println(message);
    }

    /**
     * Send message to server.
     * @param messageFromClient String.
     * @throws IOException
     */
    private void sendMessageToServer(String messageFromClient) throws IOException {
        outputStreamToServer.writeUTF(messageFromClient);
    }

    /**
     * Print message to console.
     * @param response String.
     */
    private void printMessageToClient(String response) {
        System.out.println(response);
    }

    public static void main(String[] args) throws IOException {
        Client client = new Client("127.0.0.1", 5000, "log.txt");
        client.start();
    }

}
