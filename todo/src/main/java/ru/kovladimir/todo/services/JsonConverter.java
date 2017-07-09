package ru.kovladimir.todo.services;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import ru.kovladimir.todo.model.Item;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * JSON-converter.
 */
public class JsonConverter {

    private static JsonConverter converter = new JsonConverter();

    private JsonConverter() {

    }

    /**
     * Singleton.
     * @return converter.
     */
    public static JsonConverter getInstance() {
        return converter;
    }

    /**
     * Convert list of items to JSON-format.
     * @param items List.
     * @return String.
     */
    public String getItems(List<Item> items) {
        JSONObject object = new JSONObject();
        JSONArray array = new JSONArray();
        for (Item item : items) {
            JSONObject itemJSON = new JSONObject();
            itemJSON.put("id", item.getId());
            itemJSON.put("description", item.getDescription());
            itemJSON.put("date", new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(item.getCreateDate().getTime()));
            itemJSON.put("done", item.isDone());
            array.add(itemJSON);
        }
        object.put("items", array);
        return object.toString();
    }

}
