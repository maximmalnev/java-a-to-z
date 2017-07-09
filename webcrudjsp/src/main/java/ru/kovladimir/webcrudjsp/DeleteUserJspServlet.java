package ru.kovladimir.webcrudjsp;

import ru.kovladimir.webcrudhtml.controller.DeleteUserServlet;
import ru.kovladimir.webcrudhtml.model.PostgreManager;
import ru.kovladimir.webcrudhtml.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Delete page using JSP.
 */
public class DeleteUserJspServlet extends HttpServlet {

    /**
     * {@inheritDoc}
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher view;
        String id = req.getParameter("id");
        if (id == null) {
            view = req.getRequestDispatcher("WEB-INF/view/invalidDelete.jsp");
        } else {
            User user = new User();
            user.setId(Integer.parseInt(id));
            PostgreManager.getInstance().delete(user);
            view = req.getRequestDispatcher("WEB-INF/view/validDelete.jsp");
        }
        view.forward(req, resp);
    }
}
