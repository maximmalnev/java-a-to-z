package ru.kovladimir.todo.controllers.servlets;

import ru.kovladimir.todo.model.Item;
import ru.kovladimir.todo.persistence.ItemStorage;
import ru.kovladimir.todo.services.JsonConverter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

/**
 * Servlet that send all items to client.
 */
public class GetAll extends HttpServlet {

    /**
     * Send all items to client in JSON-format.
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        Writer writer = resp.getWriter();
        List<Item> items = ItemStorage.getInstance().getAll();
        writer.append(JsonConverter.getInstance().getItems(items));
        writer.flush();
    }
}
