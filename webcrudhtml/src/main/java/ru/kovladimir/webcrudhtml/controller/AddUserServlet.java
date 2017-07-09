package ru.kovladimir.webcrudhtml.controller;

import ru.kovladimir.webcrudhtml.model.PostgreManager;
import ru.kovladimir.webcrudhtml.model.Role;
import ru.kovladimir.webcrudhtml.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Calendar;

/**
 * Add user page.
 */
public class AddUserServlet extends AbstractCrudServlet {

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

        writer.append(builder.getAddPage(req.getContextPath()));
        writer.flush();
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
        User user = getUser(req);
        PostgreManager.getInstance().add(user);
        doGet(req, resp);
    }

    /**
     * Create user by parameters.
     * @param req HttpServletRequest.
     * @return created user.
     */
    private User getUser(HttpServletRequest req) {
        User user = new User();
        user.setLogin(req.getParameter("login"));
        user.setPassword(req.getParameter("password"));
        user.setName(req.getParameter("name"));
        user.setEmail(req.getParameter("email"));
        user.setCreateDate(Calendar.getInstance());
        user.setRole(Role.getRole(req.getParameter("role")));
        return user;
    }

}
