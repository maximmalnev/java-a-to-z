package ru.kovladimir.webcrudhtml.view;

import ru.kovladimir.webcrudhtml.model.User;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Basic html.
 */
public class SimpleHTMLBuilder implements HTMLBuilder {

    /**
     * {@inheritDoc}
     * @param userList users from database.
     * @param contextPath string.
     * @return
     */
    @Override
    public String getMainPage(List<User> userList, String contextPath) {
        StringBuilder builder = new StringBuilder();
        builder.append(getHeader());
        builder.append("<body><form method='post'><table>");
        for (User user : userList) {
            builder.append("<tr>");
            builder.append(String.format("<td>%d</td>", user.getId()));
            builder.append(String.format("<td>%s</td>", user.getName()));
            builder.append(String.format("<td>%s</td>", user.getLogin()));
            builder.append(String.format("<td>%s</td>", user.getEmail()));
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String date = format.format(user.getCreateDate().getTime());
            builder.append(String.format("<td>%s</td>", date));
            builder.append(String.format("<td><input type='radio' name='id' value='%s'</td>", user.getId()));
            builder.append("</tr>");
        }
        builder.append("</table>");
        builder.append("<br>" +
                "<input type='submit' value='Add user' formaction='" + contextPath + "/add' formmethod='get'>" +
                "<input type='submit' value='Edit user' formaction='" + contextPath + "/edit' formmethod='get'>" +
                "<input type='submit' value='Delete user' formaction='" + contextPath + "/delete'>" +
                "</form></body></html>");
        return builder.toString();
    }

    /**
     * {@inheritDoc}
     * @param contextPath string.
     * @return
     */
    @Override
    public String getAddPage(String contextPath) {
        StringBuilder builder = new StringBuilder();
        builder.append(getHeader());
        builder.append("<body>\n" +
                "<form action='" + contextPath + "/add' method='post' accept-charset='UTF-8'>" +
                "<label> Name: <input type='text' name='name'></label> <br>" +
                "<label> Login: <input type='text' name='login'></label><br>" +
                "<label> Email: <input type='text' name='email'></label><br>" +
                "<input type='submit' value='Add'>" +
                "</form>" +
                "<form action='" + contextPath + "/crud' method='get' accept-charset='UTF-8'>" +
                "<input type='submit' value='Back'>" +
                "</form>" +
                "</body>\n" +
                "</html>");
        return builder.toString();
    }

    /**
     * {@inheritDoc}
     * @param contextPath string.
     * @return
     */
    @Override
    public String getValidDeletePage(String contextPath) {
        StringBuilder builder = new StringBuilder();
        builder.append(getHeader());
        builder.append("<body>");
        builder.append("Deletion was completed successfully!.<br>");
        builder.append("<form action='" + contextPath + "/crud' method='get' accept-charset='UTF-8'>" +
                "<input type='submit' value='Back'>" +
                "</form></body></html>");
        return builder.toString();
    }

    /**
     * {@inheritDoc}
     * @param contextPath string.
     * @return
     */
    @Override
    public String getInvalidDeletePage(String contextPath) {
        StringBuilder builder = new StringBuilder();
        builder.append(getHeader());
        builder.append("<body>");
        builder.append("You selected nothing! Try again!");
        builder.append("<form action='" + contextPath + "/crud' method='get' accept-charset='UTF-8'>" +
                "<input type='submit' value='Back'>" +
                "</form></body></html>");
        return builder.toString();
    }

    /**
     * {@inheritDoc}
     * @param contextPath string.
     * @param id user's id.
     * @return
     */
    @Override
    public String getValidEditFormPage(String contextPath, int id) {
        StringBuilder builder = new StringBuilder();
        builder.append(getHeader());
        builder.append("<form action='" + contextPath + "/edit' method='post' accept-charset='UTF-8'>" +
                "<input type='hidden' name='id' value='" + id + "'>" +
                "ID: " + id + "<br>" +
                "<label> Name: <input type='text' name='name'></label> <br>" +
                "<label> Login: <input type='text' name='login'></label><br>" +
                "<label> Email: <input type='text' name='email'></label><br>" +
                "<input type='submit' value='Edit'>" +
                "</form>");
        builder.append("<form action='" + contextPath + "/crud' method='get' accept-charset='UTF-8'>" +
                "<input type='submit' value='Back'>" +
                "</form></body></html>");
        return builder.toString();
    }

    /**
     * {@inheritDoc}
     * @param contextPath string.
     * @return
     */
    @Override
    public String getInvalidEditFormPage(String contextPath) {
        StringBuilder builder = new StringBuilder();
        builder.append(getHeader());
        builder.append("<body>You did not choose ID!");
        builder.append("<form action='" + contextPath + "/crud' method='get' accept-charset='UTF-8'>" +
                "<input type='submit' value='Back'>" +
                "</form></body></html>");
        return builder.toString();
    }

    /**
     * {@inheritDoc}
     * @param contextPath string.
     * @return
     */
    @Override
    public String getResponseEditPage(String contextPath) {
        StringBuilder builder = new StringBuilder();
        builder.append(getHeader());
        builder.append("<body>Edition was completed successfully!<br>" +
                "<form action='" + contextPath + "/crud' method='get' accept-charset='UTF-8'>" +
                "<input type='submit' value='Back'>" +
                "</form></body></html>");
        return builder.toString();
    }

    /**
     * Pages' header.
     * @return header.
     */
    private String getHeader() {
        return "<!DOCTYPE html>\n" +
                "<html>" +
                "<head>" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>CRUD</title>\n" +
                "</head>";
    }
}
