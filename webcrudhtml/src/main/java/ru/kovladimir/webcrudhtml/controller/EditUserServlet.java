package ru.kovladimir.webcrudhtml.controller;

import ru.kovladimir.webcrudhtml.model.Role;
import ru.kovladimir.webcrudhtml.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Calendar;

/**
 * Edit user page.
 */
public class EditUserServlet extends AbstractCrudServlet {

    /**
     * {@inheritDoc}
     *
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
        String id = req.getParameter("id");
        String page;
        if (id == null) {
            page = builder.getInvalidEditFormPage(req.getContextPath());
        } else {
            page = builder.getValidEditFormPage(req.getContextPath(), Integer.parseInt(id));
        }
        writer.append(page);
        writer.flush();
    }

    /**
     * {@inheritDoc}
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        Writer writer = resp.getWriter();

        manager.update(new User(
                        Integer.parseInt(req.getParameter("id")),
                        req.getParameter("name"),
                        req.getParameter("password"),
                        req.getParameter("login"),
                        req.getParameter("email"),
                        Calendar.getInstance(),
                        Role.getRole(req.getParameter("role"))
                )
        );

        writer.append(builder.getResponseEditPage(req.getContextPath()));
        writer.flush();
    }
}
