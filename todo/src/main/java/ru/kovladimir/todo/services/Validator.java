package ru.kovladimir.todo.services;

import ru.kovladimir.todo.model.Item;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Optional;

/**
 * User's input validator.
 */
public class Validator {

    private static Validator validator = new Validator();

    private Validator() {

    }

    /**
     * Singleton.
     * @return validator.
     */
    public static Validator getInstance() {
        return validator;
    }

    /**
     * Get item from request if they are valid.
     * @param req req.
     * @return item.
     */
    public Optional<Item> getItem(HttpServletRequest req) {
        Item item = null;
        String description = req.getParameter("description");
        if (isValid(description)) {
            item = new Item();
            item.setDescription(description);
            item.setCreateDate(new Timestamp(System.currentTimeMillis()));
        }
        return Optional.ofNullable(item);
    }

    /**
     * Check validation.
     * @param param String.
     * @return boolean.
     */
    private boolean isValid(String param) {
        return param != null && !"".equals(param);
    }
}
