package ru.kovladimir.webcrudajax;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ru.kovladimir.webcrudhtml.model.PostgreManager;
import ru.kovladimir.webcrudhtml.model.Role;
import ru.kovladimir.webcrudhtml.model.User;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * Json converter.
 */
public class JsonConverter {

    /**
     * Singleton.
     */
    private static JsonConverter converter = new JsonConverter();

    private JsonConverter() {
    }

    /**
     * Get instance.
     * @return coverter.
     */
    public static JsonConverter getInstance() {
        return converter;
    }

    /**
     * Get all users in list in JSON-format.
     * @param users List.
     * @param clientRole Role. If client role == user, then don't include password.
     * @return JSON-String.
     */
    public String getUsers(List<User> users, Role clientRole) {
        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();
        for (User user : users) {
            JSONObject userJSON = new JSONObject();
            userJSON.put("id", user.getId());
            userJSON.put("login", user.getLogin());
            if (clientRole == Role.ADMIN) {
                userJSON.put("password", user.getPassword());
            }
            userJSON.put("name", user.getName());
            userJSON.put("email", user.getEmail());
            userJSON.put("date", new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(user.getCreateDate().getTime()));
            userJSON.put("role", user.getRole().toString());
            array.add(userJSON);
        }
        object.put("users", array);
        return object.toString();
    }

    /**
     * Get info about user in JSON-format.
     * @param user User.
     * @return JSON-String.
     */
    public String getClientInfo(User user) {
        JSONObject userJSON = new JSONObject();
        userJSON.put("id", user.getId());
        userJSON.put("login", user.getLogin());
        userJSON.put("password", user.getPassword());
        userJSON.put("name", user.getName());
        userJSON.put("email", user.getEmail());
        userJSON.put("date", new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(user.getCreateDate().getTime()));
        userJSON.put("role", user.getRole().toString());
        return userJSON.toString();
    }

}
