package ru.kovladimir.webcrudjsp;

import ru.kovladimir.webcrudhtml.controller.EditUserServlet;
import ru.kovladimir.webcrudhtml.model.PostgreManager;
import ru.kovladimir.webcrudhtml.model.Role;
import ru.kovladimir.webcrudhtml.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.Optional;

/**
 * Edit page using JSP.
 */
public class EditUserJspServlet extends HttpServlet {

    /**
     * {@inheritDoc}
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher view;
        String id = req.getParameter("id");
        if (id == null) {
            view = req.getRequestDispatcher("WEB-INF/view/invalidEdit.jsp");
        } else {
            Optional<User> userOptional = PostgreManager.getInstance().getUser(Integer.parseInt(id));
            if (userOptional.isPresent()) {
                req.setAttribute("user", userOptional.get());
                view = req.getRequestDispatcher("WEB-INF/view/validEdit.jsp");
            } else {
                view = req.getRequestDispatcher("WEB-INF/view/invalidEdit.jsp");
            }
        }
        view.forward(req, resp);
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
        PostgreManager.getInstance().update(new User(
                        Integer.parseInt(req.getParameter("id")),
                        req.getParameter("name"),
                        req.getParameter("password"),
                        req.getParameter("login"),
                        req.getParameter("email"),
                        Calendar.getInstance(),
                        Role.getRole(req.getParameter("role"))
                )
        );
        RequestDispatcher view = req.getRequestDispatcher("WEB-INF/view/editResponse.jsp");
        view.forward(req, resp);
    }
}
