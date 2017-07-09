package ru.kovladimir.webcrudajax.servlets;

import ru.kovladimir.webcrudajax.JsonConverter;
import ru.kovladimir.webcrudhtml.model.PostgreManager;
import ru.kovladimir.webcrudhtml.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Writer;
import java.util.Optional;

/**
 * Sign in.
 */
public class SignIn extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Optional<User> user = PostgreManager.getInstance().getUser(login, password);
        Writer writer = resp.getWriter();
        if (user.isPresent()) { // means that login and password are valid
            User client = user.get();
            HttpSession session = req.getSession();
            session.setAttribute("client", client);
            writer.append(JsonConverter.getInstance().getClientInfo(client));
        } else {
            writer.append("error");
        }
        writer.flush();
    }
}
