package ru.kovladimir.webcrudajax.servlets;

import ru.kovladimir.webcrudajax.JsonConverter;
import ru.kovladimir.webcrudhtml.model.PostgreManager;
import ru.kovladimir.webcrudhtml.model.Role;
import ru.kovladimir.webcrudhtml.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

/**
 * Get all users' info.
 */
public class UsersInfoGetter extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        Writer writer = resp.getWriter();
        List<User> users = PostgreManager.getInstance().getAll();
        User client = (User) req.getSession().getAttribute("client");
        writer.append(JsonConverter.getInstance().getUsers(users, client.getRole()));
        writer.flush();
    }
}
