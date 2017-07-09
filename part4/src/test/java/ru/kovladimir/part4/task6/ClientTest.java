package ru.kovladimir.part4.task6;

import org.junit.Ignore;
import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClientTest {

    @Ignore
    @Test
    public void whenCloseConnectionThenWholeDialogIsInLog() throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("Hi!".getBytes("UTF-8"));
        Socket socket = mock(Socket.class);
        when(socket.getInputStream()).thenReturn(byteArrayInputStream);
        when(socket.getOutputStream()).thenReturn(new ByteArrayOutputStream());

        Client client = new Client("127.0.0.1", 5000, "log.txt") {
            @Override
            protected Socket createSocket(InetAddress inetAddress, int serverPort) {
                return socket;
            }
        };

        String separator = System.lineSeparator();
        InputStream in = new ByteArrayInputStream(("Hello" + separator + "стоп" + separator + "123" + separator +
                "продолжить" + separator + "45" + separator + "123" + separator + "закончить" + separator).getBytes());
        InputStream defaultIn = System.in;

        System.setIn(in);
        client.start();
        System.setIn(defaultIn);

        String log = getLogFromFile();

        assertEquals(log, "HelloHi!стоп123продолжитьnull45null123nullзакончить");
    }

    public String getLogFromFile() throws FileNotFoundException {
        StringBuilder builder = new StringBuilder();
        Scanner scanner = new Scanner(new FileInputStream("log.txt"));
        while (scanner.hasNextLine()) {
            builder.append(scanner.nextLine());
        }
        scanner.close();
        return builder.toString();
    }

}