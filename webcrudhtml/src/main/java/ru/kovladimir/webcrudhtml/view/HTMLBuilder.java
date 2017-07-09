package ru.kovladimir.webcrudhtml.view;

import ru.kovladimir.webcrudhtml.model.User;

import java.util.List;

/**
 * HTMP-pages generator,
 */
public interface HTMLBuilder {

    /**
     * Get main page with table and buttons.
     * @param userList users from database.
     * @param contextPath string.
     * @return page.
     */
    String getMainPage(List<User> userList, String contextPath);

    /**
     * Get page to add user.
     * @param contextPath string.
     * @return page.
     */
    String getAddPage(String contextPath);

    /**
     * Get page to delete user if client chose user's id.
     * @param contextPath string.
     * @return page.
     */
    String getValidDeletePage(String contextPath);

    /**
     * Get error page if client didn't choose user's id.
     * @param contextPath string.
     * @return page.
     */
    String getInvalidDeletePage(String contextPath);

    /**
     * Get page to edit user if client chose user's id.
     * @param contextPath string.
     * @param id user's id.
     * @return page.
     */
    String getValidEditFormPage(String contextPath, int id);

    /**
     * Get error page if client didn't choose user's id.
     * @param contextPath string.
     * @return page.
     */
    String getInvalidEditFormPage(String contextPath);

    /**
     * Get page when edition was completed.
     * @param contextPath string.
     * @return page.
     */
    String getResponseEditPage(String contextPath);

}
