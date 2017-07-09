package ru.kovladimir.webcrudhtml.model;

import ru.kovladimir.webcrud.PoolInitializer;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

/**
 * PostgreSQL manager.
 */
public class PostgreManager implements DBManager {

    private static PostgreManager manager = new PostgreManager();

    private PostgreManager() {
        try (Connection connection = PoolInitializer.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "SELECT setval('users_id_seq', COALESCE((SELECT MAX(id)+1 FROM users), 1), FALSE);"
             )) {
            statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DBManager getInstance() {
        return manager;
    }


    /**
     * {@inheritDoc}
     *
     * @param user new user.
     */
    @Override
    public List<User> add(User user) {
        List<User> users = new ArrayList<>();
        try (Connection connection = PoolInitializer.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO users " +
                     "(login, password, name, email, create_date, role_id) VALUES (?, ?, ?, ?, ?, ?)")) {
            int roleId = getRoleId(user.getRole(), connection);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getEmail());
            statement.setTimestamp(5, new Timestamp(user.getCreateDate().getTimeInMillis()));
            statement.setInt(6, roleId);
            statement.executeUpdate();
            collectAllUsers(connection, users);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    /**
     * {@inheritDoc}
     *
     * @param user user.
     */
    @Override
    public List<User> update(User user) {
        List<User> users = new ArrayList<>();

        try (Connection connection = PoolInitializer.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE users SET " +
                     "login = ?, password = ?, name = ?, email = ?, role_id = ? WHERE id = ?")) {
            int roleId = getRoleId(user.getRole(), connection);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getEmail());
            statement.setInt(5, roleId);
            statement.setInt(6, user.getId());
            statement.executeUpdate();
            collectAllUsers(connection, users);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;

    }

    /**
     * {@inheritDoc}
     *
     * @param user user to delete.
     */
    @Override
    public List<User> delete(User user) {
        List<User> users = new ArrayList<>();

        try (Connection connection = PoolInitializer.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
            statement.setInt(1, user.getId());
            statement.executeUpdate();
            collectAllUsers(connection, users);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;

    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = PoolInitializer.getConnection()) {
            collectAllUsers(connection, users);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


    /**
     * {@inheritDoc}
     *
     * @param login    String.
     * @param password String.
     * @return
     */
    @Override
    public Optional<User> getUser(String login, String password) {
        Optional<User> userOptional = Optional.empty();
        try (Connection connection = PoolInitializer.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT id, login, password, name, email, " +
                     "create_date, role_name FROM users INNER JOIN role ON users.role_id = role.role_id WHERE " +
                     "login = ? AND password = ?")) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                userOptional = Optional.of(getUser(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userOptional;
    }

    /**
     * {@inheritDoc}
     *
     * @param id int.
     * @return
     */
    @Override
    public Optional<User> getUser(int id) {
        Optional<User> userOptional = Optional.empty();
        try (Connection connection = PoolInitializer.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT id, login, password, name, email, " +
                     "create_date, role_name FROM users INNER JOIN role ON users.role_id = role.role_id WHERE id = ?")) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Calendar date = Calendar.getInstance();
                date.setTimeInMillis(resultSet.getTimestamp("create_date").getTime());
                userOptional = Optional.of(getUser(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userOptional;
    }

    /**
     * Get user from result set.
     *
     * @param resultSet ResultSet.
     * @return User.
     * @throws SQLException
     */
    private User getUser(ResultSet resultSet) throws SQLException {
        Calendar date = Calendar.getInstance();
        date.setTimeInMillis(resultSet.getTimestamp("create_date").getTime());
        return new User(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("password"),
                resultSet.getString("login"),
                resultSet.getString("email"),
                date,
                Role.getRole(resultSet.getString("role_name"))
        );
    }

    /**
     * Get role if by Role enum.
     *
     * @param role
     * @param connection
     * @return
     */
    private int getRoleId(Role role, Connection connection) {
        int id = 0;
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT role_id FROM role WHERE role_name = ?"
        )) {
            statement.setString(1, role.toString().toUpperCase());
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("role_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    private void collectAllUsers(Connection connection, List<User> users) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("SELECT id, login, password, name, email, " +
                "create_date, role_name FROM users INNER JOIN role ON users.role_id = role.role_id ORDER BY id")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Calendar date = Calendar.getInstance();
                date.setTimeInMillis(resultSet.getTimestamp("create_date").getTime());
                users.add(new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("password"),
                        resultSet.getString("login"),
                        resultSet.getString("email"),
                        date,
                        Role.getRole(resultSet.getString("role_name"))
                ));
            }
        }
    }

}