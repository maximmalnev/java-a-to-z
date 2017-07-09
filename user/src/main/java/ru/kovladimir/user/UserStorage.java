package ru.kovladimir.user;

/**
 * User storage to keep users.
 */
public class UserStorage {

    /**
     * Array of users.
     */
    private User[] users = new User[5];

    /**
     * Validation to start data,
     */
    private Validate validation = new Validation();

    /**
     * Default constructor.
     * @param validation Validate.
     */
    public UserStorage(Validate validation) {
		this.validation = validation;
	}

	/**
     * Getter.
     * @return User[].
     */
    public User[] getUsers() {
        return users;
    }

    /**
     * Add user to storage.
     * @param user User.
     * @return wasAdded.
     */
    public boolean add(User user) {
        boolean wasAdded = false;
        if (validation.isValid(user)) {
            for (int i = 0; i < users.length; i++) {
                if (users[i] == null) {
                    users[i] = user;
                    wasAdded = true;
                    break;
                }
            }
        }
        return wasAdded;
    }

    /**
     * Edit user in storage.
     * @param user User.
     * @return wasEdited.
     */
    public boolean edit(User user) {
        boolean wasEdited = false;
        if (validation.isValid(user)) {
            long id = user.getId();
            for (int i = 0; i < users.length; i++) {
                if (users[i] != null && users[i].getId() == id) {
                    users[i] = user;
                    wasEdited = true;
                    break;
                }
            }
        }
        return wasEdited;
    }

    /**
     * Delete user from storage by id.
     * @param id long.
     * @return wasDeleted.
     */
    public boolean delete(long id) {
        boolean wasDeleted = false;
        for (int i = 0; i < users.length; i++) {
            if (users[i] != null && users[i].getId() == id) {
                users[i] = null;
                wasDeleted = true;
                break;
            }
        }
        return wasDeleted;
    }

    /**
     * Print all users in storage.
     */
    public void printUsers() {
        for (User user : users) {
            System.out.println(user);
        }
    }
}
