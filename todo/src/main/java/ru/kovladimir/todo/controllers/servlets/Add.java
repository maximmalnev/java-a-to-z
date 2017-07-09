package ru.kovladimir.todo.controllers.servlets;

import ru.kovladimir.todo.model.Item;
import ru.kovladimir.todo.persistence.ItemStorage;
import ru.kovladimir.todo.services.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * Servlet that can add new item from client.
 */
public class Add extends HttpServlet{

    /**
     * Add new item.
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<Item> itemOptional = Validator.getInstance().getItem(req);
        if (itemOptional.isPresent()) {
            ItemStorage.getInstance().create(itemOptional.get());
        }
    }
}
