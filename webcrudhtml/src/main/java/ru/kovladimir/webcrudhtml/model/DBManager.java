package ru.kovladimir.webcrudhtml.model;

import java.util.List;
import java.util.Optional;

/**
 * Database manager.
 */
public interface DBManager {

    /**
     * Add user to database.
     * @param user new user.
     */
    List<User>  add(User user);

    /**
     * Update user's info with the same ID in database.
     * @param user user.
     */
    List<User>  update(User user);

    /**
     * Delete user from database.
     * @param user user to delete.
     */
    List<User>  delete(User user);

    /**
     * Get all users from database.
     * @return list of users.
     */
    List<User> getAll();

    /**
     * Get user by login and password.
     * @param login String.
     * @param password String.
     * @return Empty optional if invalid login-password.
     */
    Optional<User> getUser(String login, String password);

    /**
     * Get user by id.
     * @param id int.
     * @return Empty optional if invalid id.
     */
    Optional<User> getUser(int id);
}
