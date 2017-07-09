package ru.kovladimir.user;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserStorageTest {


    @Test
    public void whenAddValidUserThenArrayContainsIt() {
        UserStorage storage = new UserStorage(new Validation());
        User user = new User(123, "Ann", 30);

        storage.add(user);
        boolean containsUser = Arrays.asList(storage.getUsers()).contains(user);

        assertTrue(containsUser);
    }

    @Test
    public void whenAddNotValidUserThenArrayContainsIt() {
        UserStorage storage = new UserStorage(new Validation());
        User user = new User(123, "Ann", -30);

        storage.add(user);
        boolean containsUser = Arrays.asList(storage.getUsers()).contains(user);

        assertFalse(containsUser);
    }

    @Test
    public void whenEditValidUserThenArrayContainsIt() {
        long id = 123;
        UserStorage storage = new UserStorage(new Validation());
        User oldUser = new User(id, "Ann", 30);
        User newUser = new User(id, "Jack", 25);
        storage.add(oldUser);

        storage.edit(newUser);
        boolean containsUser = Arrays.asList(storage.getUsers()).contains(newUser);

        assertTrue(containsUser);
    }

    @Test
    public void whenDeleteValidUserThenArrayDoesNotContainsIt() {
        long id = 123;
        UserStorage storage = new UserStorage(new Validation());
        User sser = new User(id, "Ann", 30);
        storage.add(sser);

        storage.delete(id);
        boolean containsUser = Arrays.asList(storage.getUsers()).contains(sser);

        assertFalse(containsUser);
    }

}