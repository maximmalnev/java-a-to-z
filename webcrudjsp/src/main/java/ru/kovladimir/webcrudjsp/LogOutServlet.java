package ru.kovladimir.webcrudjsp;

import ru.kovladimir.webcrudhtml.controller.AbstractCrudServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Log out.
 */
public class LogOutServlet extends HttpServlet {

    /**
     * {@inheritDoc}
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        req.getRequestDispatcher("WEB-INF/view/signin.jsp").forward(req, resp);
    }
}
