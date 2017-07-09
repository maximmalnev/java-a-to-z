package ru.kovladimir.threads;

import java.util.ArrayList;
import java.util.List;

/**
 * User storage.
 */
public class UserStorage {

    /**
     * Users.
     */
    private final List<User> users = new ArrayList<>();

    /**
     * ADd user to storage.
     *
     * @param user User.
     */
    public synchronized void add(User user) {
        users.add(user);
    }

    /**
     * Replace user with this ID.
     *
     * @param user User.
     */
    public synchronized void edit(User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).equals(user)) {
                users.set(i, user);
                break;
            }
        }
    }

    /**
     * Delete user with this id.
     *
     * @param id int.
     */
    public synchronized void delete(int id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                users.remove(i);
                break;
            }
        }
    }

    /**
     * Print users.
     */
    public synchronized void printUsers() {
        for (User user :
                users) {
            System.out.printf("User ID: %d. Amount: %d$", user.id, user.amount);
        }
    }

    /**
     * Transfer money between users.
     *
     * @param senderID         int.
     * @param receiverID       int.
     * @param amountToTransfer int.
     */
    public synchronized void transfer(int senderID, int receiverID, int amountToTransfer) {
        User sender = getUserByID(senderID);
        User receiver = getUserByID(receiverID);

        if (sender != null && receiver != null) {
            if (sender.amount >= amountToTransfer) {
                sender.setAmount(sender.getAmount() - amountToTransfer);
                receiver.setAmount(receiver.getAmount() + amountToTransfer);
            }
        }
    }

    /**
     * Get user by id from list.
     *
     * @param id int.
     * @return User.
     */
    private User getUserByID(int id) {
        User user = null;
        for (User currentUser : users) {
            if (id == currentUser.getId()) {
                user = currentUser;
                break;
            }
        }
        return user;
    }

    /**
     * User.
     */
    public static class User {

        /**
         * ID.
         */
        private int id;

        /**
         * Money.
         */
        private int amount;

        /**
         * Constructor.
         *
         * @param id int.
         */
        public User(int id) {
            this(id, 0);
        }

        /**
         * Constructor with amount.
         *
         * @param id     id.
         * @param amount amount.
         */
        public User(int id, int amount) {
            this.id = id;
            this.amount = amount;
        }

        public int getId() {
            return id;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            User user = (User) o;

            return id == user.id;

        }

        @Override
        public int hashCode() {
            return id;
        }
    }
}
