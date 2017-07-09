package ru.kovladimir.webdao.dao.persistence;

import ru.kovladimir.webcrud.PoolInitializer;
import ru.kovladimir.webdao.dao.model.Address;
import ru.kovladimir.webdao.dao.model.MusicType;
import ru.kovladimir.webdao.dao.model.Role;
import ru.kovladimir.webdao.dao.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vladimir on 18.10.2016.
 */
public class UserStorage implements DAO<User>, UserRepository {
    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = PoolInitializer.getConnection();
             PreparedStatement statement = connection.prepareStatement("select * from users;")
        ) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User get(long id) {
        User user = null;
        try (Connection connection = PoolInitializer.getConnection();
             PreparedStatement statement = connection.prepareStatement("select * from users where id = ?")
        ) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void add(User element) {
        try (Connection connection = PoolInitializer.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO users " +
                     "(login, password) VALUES (?, ?)")) {
            statement.setString(1, element.getLogin());
            statement.setString(2, element.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User element) {
        try (Connection connection = PoolInitializer.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE users SET " +
                     "login = ?, password = ? WHERE id = ?")) {
            statement.setString(1, element.getLogin());
            statement.setString(2, element.getPassword());
            statement.setLong(3, element.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(User element) {
        try (Connection connection = PoolInitializer.getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
            statement.setLong(1, element.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Role getRole(User user) {
        return null;
    }

    @Override
    public Address getAddress(User user) {
        return null;
    }

    @Override
    public List<MusicType> getMusicTypes(User user) {
        return null;
    }

    @Override
    public void add(User user, Role role, Address address, List<MusicType> types) {

    }

    @Override
    public List<User> getUsers(Role role) {
        return null;
    }

    @Override
    public User getUser(Address address) {
        return null;
    }

    @Override
    public List<User> getUsers(MusicType type) {
        return null;
    }
}
