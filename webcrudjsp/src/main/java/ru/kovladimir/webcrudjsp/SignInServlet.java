package ru.kovladimir.webcrudjsp;

import ru.kovladimir.webcrudhtml.model.PostgreManager;
import ru.kovladimir.webcrudhtml.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

/**
 * Sign in.
 */
public class SignInServlet extends HttpServlet {

    /**
     * {@inheritDoc}
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/view/signin.jsp").forward(req, resp);
    }

    /**
     * {@inheritDoc}
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Optional<User> user = PostgreManager.getInstance().getUser(login, password);
        if (user.isPresent()) { // means that login and password are valid
            HttpSession session = req.getSession();
            session.setAttribute("user", user.get());
            resp.sendRedirect(String.format("%s/crud", req.getContextPath()));
        } else {
            req.setAttribute("message", new Object());
            doGet(req, resp);
        }
    }
}
