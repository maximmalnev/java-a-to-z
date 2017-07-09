package ru.kovladimir.webcrudhtml.controller;

import ru.kovladimir.webcrudhtml.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

/**
 * Main page.
 */
public class MainServlet extends AbstractCrudServlet {

    /**
     * {@inheritDoc}
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        Writer writer = resp.getWriter();

        List<User> users = manager.getAll();
        writer.append(builder.getMainPage(users, req.getContextPath()));
        writer.flush();
    }

}
