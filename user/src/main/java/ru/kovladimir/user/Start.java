package ru.kovladimir.user;

public class Start {
    public static void main(String[] args) {
        UserStorage storage = new UserStorage(new Validation());
        storage.add(new User(123, "Nick", 22));
        storage.printUsers();
        System.out.println();

        storage.add(new User(523, "Jack", 33));
        storage.printUsers();
        System.out.println();

        // not valid
        storage.add(new User(523, null, 33));
        storage.printUsers();
        System.out.println();

        storage.edit(new User(523, "John", 32));
        storage.printUsers();
        System.out.println();

        storage.delete(123);
        storage.printUsers();
        System.out.println();
    }
}
