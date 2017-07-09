package ru.kovladimir.threads;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserStorageTest {

    @Test
    public void whenAddUserThenContainsIt() {
        PrintStream oldOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        UserStorage storage = new UserStorage();
        UserStorage.User user = new UserStorage.User(1);

        storage.add(user);
        storage.printUsers();
        System.setOut(oldOut);

        assertThat(outContent.toString(), is("User ID: 1. Amount: 0$"));
    }

    @Test
    public void whenEditUserThenContainsIt() {
        PrintStream oldOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        UserStorage storage = new UserStorage();
        UserStorage.User user = new UserStorage.User(1);
        storage.add(user);

        storage.edit(new UserStorage.User(1, 100));
        storage.printUsers();
        System.setOut(oldOut);

        assertThat(outContent.toString(), is("User ID: 1. Amount: 100$"));
    }

    @Test
    public void whenDeleteUserThenDoesNotContainIt() {
        PrintStream oldOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        UserStorage storage = new UserStorage();
        UserStorage.User user = new UserStorage.User(1);
        storage.add(user);

        storage.delete(1);
        storage.printUsers();
        System.setOut(oldOut);

        assertThat(outContent.toString(), is(""));
    }

    @Test
    public void whenTransferMoneyThenSenderWillNotHaveIt() {
        UserStorage storage = new UserStorage();
        UserStorage.User user1 = new UserStorage.User(1, 200);
        UserStorage.User user2 = new UserStorage.User(2);
        storage.add(user1);
        storage.add(user2);

        storage.transfer(1, 2, 150);

        assertThat(user1.getAmount(), is(50));
    }

    @Test
    public void whenTransferMoneyThenReceiverWillHaveIt() {
        UserStorage storage = new UserStorage();
        UserStorage.User user1 = new UserStorage.User(1, 200);
        UserStorage.User user2 = new UserStorage.User(2);
        storage.add(user1);
        storage.add(user2);

        storage.transfer(1, 2, 150);

        assertThat(user2.getAmount(), is(150));
    }
}