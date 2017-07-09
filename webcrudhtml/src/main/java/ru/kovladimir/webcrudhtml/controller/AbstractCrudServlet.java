package ru.kovladimir.webcrudhtml.controller;

import ru.kovladimir.webcrudhtml.model.DBManager;
import ru.kovladimir.webcrudhtml.view.HTMLBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * Abstract CRUD Servlet to get html builder and DB manager.
 */
public abstract class AbstractCrudServlet extends HttpServlet {

    /**
     * HTML-pages creator.
     */
    protected HTMLBuilder builder;

    /**
     * Connect to database.
     */
    protected DBManager manager;

    /**
     * {@inheritDoc}
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        builder = (HTMLBuilder) getServletContext().getAttribute("builder");
        manager = (DBManager) getServletContext().getAttribute("manager");
    }

}
