package ru.kovladimir.webcrudhtml.controller;

import ru.kovladimir.webcrudhtml.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * Delete user page.
 */
public class DeleteUserServlet extends AbstractCrudServlet {

    /**
     * {@inheritDoc}
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
        String id = req.getParameter("id");
        String page;
        if (id == null) {
            page = builder.getInvalidDeletePage(req.getContextPath());
        } else {
            User user = new User();
            user.setId(Integer.parseInt(id));
            manager.delete(user);
            page = builder.getValidDeletePage(req.getContextPath());
        }
        writer.append(page);
        writer.flush();
    }
}
