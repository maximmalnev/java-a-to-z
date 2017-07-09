package ru.kovladimir.webcrudajax;

import ru.kovladimir.webcrudhtml.model.Role;
import ru.kovladimir.webcrudhtml.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Optional;

/**
 * Validator.
 */
public class Validator {

    /**
     * Singleton.
     */
    private static Validator validator = new Validator();

    private Validator() {

    }

    /**
     * Get instance.
     * @return Validator.
     */
    public static Validator getInstance() {
        return validator;
    }

    /**
     * Return user for addition to DB by parameters.
     * @param req HttpServletRequest.
     * @return User.
     */
    public Optional<User> getUserForCreation(HttpServletRequest req) {
        User user = null;
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        Role role = Role.getRole(req.getParameter("role"));
        if (isValid(login) && isValid(password) && isValid(name) && isValid(email) && isValid(role)) {
            user = new User();
            user.setLogin(login);
            user.setPassword(password);
            user.setName(name);
            user.setEmail(email);
            user.setCreateDate(Calendar.getInstance());
            user.setRole(role);
        }
        return Optional.ofNullable(user);
    }

    /**
     * Return user for edition to DB by parameters.
     * @param req HttpServletRequest.
     * @return User.
     */
    public Optional<User> getUserForEdition(HttpServletRequest req, Role clientRole) {
        User user = null;
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String login = req.getParameter("login");
            String password = req.getParameter("password");
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            Role role = clientRole == Role.ADMIN ? Role.getRole(req.getParameter("role")) : clientRole;
            if (isValid(id) && isValid(login) && isValid(password)
                    && isValid(name) && isValid(email) && isValid(role)) {
                user = new User(id, name, password, login, email, Calendar.getInstance(), role);
            }
        } catch (NumberFormatException e) { // id validation if not int
            e.printStackTrace();
        }
        return Optional.ofNullable(user);
    }

    /**
     * Return user for deletion to DB by parameters.
     * @param req HttpServletRequest.
     * @return User.
     */
    public Optional<User> getUserForDeletion(HttpServletRequest req) {
        User user = null;
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            if (isValid(id)) {
                user = new User();
                user.setId(id);
            }
        } catch (NumberFormatException e) { // id validation if not int
            e.printStackTrace();
        }
        return Optional.ofNullable(user);
    }

    /**
     * Check is string parameter valid.
     * @param param String.
     * @return boolean.
     */
    private boolean isValid(String param) {
        return param != null && !"".equals(param);
    }

    /**
     * Check is Role parameter valid.
     * @param role Role.
     * @return boolean.
     */
    private boolean isValid(Role role) {
        return role != null;
    }

    /**
     * Check is int parameter valid.
     * @param id int.
     * @return boolean.
     */
    private boolean isValid(int id) {
        return id > 0;
    }

}
