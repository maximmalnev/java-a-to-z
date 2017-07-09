package ru.kovladimir.part4.task5;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

/**
 * Chat.
 */
public class Chat {

    /**
     * Path to file with responses.
     */
    private final String FILE_WITH_RESPONSES;

    /**
     * Path to file to log.
     */
    private final String LOG_FILE;

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
     * All responses.
     */
    private String[] responses;

    /**
     * Stream to printResult log.
     */
    private PrintStream logStream;

    /**
     * Stream to scan string from user.
     */
    private Scanner scannerFromUser;

    /**
     * Random to get random response.
     */
    private Random random;

    /**
     * Pause flag.
     */
    private boolean pause = false;

    /**
     * Exit flag.
     */
    private boolean exit = false;

    /**
     * Constructor.
     * @param file_with_responses String.
     * @param log_file String.
     */
    public Chat(String file_with_responses, String log_file) {
        FILE_WITH_RESPONSES = file_with_responses;
        LOG_FILE = log_file;
    }

    /**
     * Main loop.
     * @throws IOException
     */
    public void start() throws IOException {
        fillResponsesFromFile();
        init();
        String userString;
        do {
            userString = scannerFromUser.nextLine();
            logStream.println(userString);
            if (PAUSE.equals(userString)) {
                pause = true;
            } else if (START.equals(userString)) {
                pause = false;
            } else if (EXIT.equals(userString)) {
                exit = true;
            } else if (!pause && !exit) {
                reply();
            }
        } while (!exit);
        closeAllStreams();
    }

    /**
     * Init streams and Random.
     * @throws IOException
     */
    public void init() throws IOException {
        logStream = new PrintStream(new FileOutputStream(LOG_FILE));
        scannerFromUser = new Scanner(System.in);
        random = new Random();
    }

    /**
     * Reply to user from array.
     */
    public void reply() {
        String response = responses[random.nextInt(responses.length)];
        System.out.println(response);
        logStream.println(response);
    }

    /**
     * Close all streams.
     */
    public void closeAllStreams() {
        logStream.close();
        scannerFromUser.close();
    }

    /**
     * Get all lines from file to array.
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

    public static void main(String[] args) throws IOException {
        Chat chat = new Chat("responses.txt", "log.txt");
        chat.start();
    }

}
