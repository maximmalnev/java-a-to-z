package ru.kovladimir.webcrudhtml.model;

import java.util.Calendar;

/**
 * User.
 */
public class User {

    private int id;

    private String name;

    private String password;

    private String login;

    private String email;

    private Calendar createDate;

    private Role role;

    public User() {
    }

    public User(int id, String name, String password, String login, String email, Calendar createDate, Role role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.login = login;
        this.email = email;
        this.createDate = createDate;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Calendar getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Calendar createDate) {
        this.createDate = createDate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
