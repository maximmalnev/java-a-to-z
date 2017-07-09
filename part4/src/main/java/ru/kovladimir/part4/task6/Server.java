package ru.kovladimir.part4.task6;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

/**
 * Server.
 */
public class Server {

    /**
     * Path tp file woth responses.
     */
    private final String FILE_WITH_RESPONSES;

    /**
     * Port.
     */
    private int port;

    /**
     * All responses to answer.
     */
    private String[] responses;

    /**
     * Random.
     */
    private Random random = new Random();

    /**
     * Pause flag.
     */
    private boolean pause = false;

    /**
     * Serversocket.
     */
    private ServerSocket serverSocket;

    /**
     * Client Socket.
     */
    private Socket clientSocket;

    /**
     * Stream from client.
     */
    private DataInputStream inputStreamFromClient;

    /**
     * Stream to client.
     */
    private DataOutputStream outputStreamToClient;

    /**
     * COnstructor.
     * @param port port.
     * @param file_with_responses String.
     */
    public Server(int port, String file_with_responses) {
        this.port = port;
        this.FILE_WITH_RESPONSES = file_with_responses;
    }

    /**
     * Start server. Main loop.
     */
    public void start()  {
        try {
            fillResponsesFromFile();
            init();
            while (clientSocket.isConnected()) {
                getMessageFromClient();
                replyToUser();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Close server.
     * @throws IOException
     */
    public void close() throws IOException {
        serverSocket.close();
    }

    /**
     * Read responses from file.
     * @throws FileNotFoundException
     */
    private void fillResponsesFromFile() throws FileNotFoundException {
        int responsesQuantity = countStringsInFile();
        responses = new String[responsesQuantity];
        Scanner scanner = new Scanner(new FileInputStream(FILE_WITH_RESPONSES));
        for (int i = 0; i < responses.length; i++) {
            responses[i] = scanner.nextLine();
        }
        scanner.close();
    }

    /**
     * Count lines in file.
     *
     * @return int.
     * @throws FileNotFoundException
     */
    private int countStringsInFile() throws FileNotFoundException {
        int count = 0;
        Scanner scanner = new Scanner(new FileInputStream(FILE_WITH_RESPONSES));
        while (scanner.hasNextLine()) {
            scanner.nextLine();
            count++;
        }
        scanner.close();
        return count;
    }

    /**
     * Init.
     * @throws IOException
     */
    private void init() throws IOException {
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        inputStreamFromClient = new DataInputStream(clientSocket.getInputStream());
        outputStreamToClient = new DataOutputStream(clientSocket.getOutputStream());
    }

    /**
     * Get message from client.
     * @throws IOException
     */
    private void getMessageFromClient() throws IOException {
        String message = inputStreamFromClient.readUTF();
    }

    /**
     * Reply to user from array.
     */
    private void replyToUser() throws IOException {
        if (!pause) {
            outputStreamToClient.writeUTF(responses[random.nextInt(responses.length)]);
            outputStreamToClient.flush();
        }
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server(5000, "responses.txt");
        server.start();
    }
}
