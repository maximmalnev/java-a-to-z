package ru.kovladimir.webcrudajax.servlets;

import ru.kovladimir.webcrudajax.JsonConverter;
import ru.kovladimir.webcrudajax.Validator;
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
import java.util.Optional;

/**
 * Delete user and return all users info.
 */
public class DeleteUser extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<User> user = Validator.getInstance().getUserForDeletion(req);
        if (user.isPresent()) {
            Writer writer = resp.getWriter();
            List<User> users = PostgreManager.getInstance().delete(user.get());
            User client = (User) req.getSession().getAttribute("client");
            writer.append(JsonConverter.getInstance().getUsers(users, client.getRole()));
            writer.flush();
        }
    }
}
