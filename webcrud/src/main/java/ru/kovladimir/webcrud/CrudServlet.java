package ru.kovladimir.webcrud;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.sql.*;
import java.text.SimpleDateFormat;

/**
 * Simple CRUD Servlet.
 */
public class CrudServlet extends HttpServlet {

    /**
     * Get all users.
     *
     * @param req  HttpServletRequest.
     * @param resp HttpServletResponse.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Writer writer = resp.getWriter();
        Connection connection = PoolInitializer.getConnection();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM users_old")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                writer.append(getUserInfo(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        writer.flush();
    }

    /**
     * Update user's info by id.
     *
     * @param req  HttpServletRequest.
     * @param resp HttpServletResponse.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = PoolInitializer.getConnection();
        try (PreparedStatement statement = connection.prepareStatement("UPDATE users_old SET " +
                "name = ?, login = ?, email = ? WHERE id = ?")) {
            statement.setString(1, req.getParameter("name"));
            statement.setString(2, req.getParameter("login"));
            statement.setString(3, req.getParameter("email"));
            statement.setInt(4, Integer.parseInt(req.getParameter("id")));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Add user.
     *
     * @param req  HttpServletRequest.
     * @param resp HttpServletResponse.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = PoolInitializer.getConnection();
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO users_old " +
                "(name, login, email, create_date) VALUES (?, ?, ?, ?)")) {
            statement.setString(1, req.getParameter("name"));
            statement.setString(2, req.getParameter("login"));
            statement.setString(3, req.getParameter("email"));
            statement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete user.
     *
     * @param req  HttpServletRequest.
     * @param resp HttpServletResponse.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = PoolInitializer.getConnection();
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM users_old " +
                "WHERE id = ?")) {
            statement.setInt(1, Integer.parseInt(req.getParameter("id")));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get info about current user in set.
     *
     * @param resultSet ResultSet.
     * @return String.
     */
    private String getUserInfo(ResultSet resultSet) {
        String info = null;
        try {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String login = resultSet.getString("login");
            String email = resultSet.getString("email");

            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String date = format.format(new Date(resultSet.getTimestamp("create_date").getTime()));

            info = String.format("%d | %s | %s | %s | %s%n", id, name, login, email, date);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return info;
    }
}
