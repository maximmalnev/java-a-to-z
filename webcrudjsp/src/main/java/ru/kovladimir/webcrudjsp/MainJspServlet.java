package ru.kovladimir.webcrudjsp;

import ru.kovladimir.webcrudhtml.controller.MainServlet;
import ru.kovladimir.webcrudhtml.model.PostgreManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Main page using JSP.
 */
public class MainJspServlet extends MainServlet {

    /**
     * {@inheritDoc}
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", PostgreManager.getInstance().getAll());
        RequestDispatcher view = req.getRequestDispatcher("WEB-INF/view/main.jsp");
        view.forward(req, resp);
    }
}
