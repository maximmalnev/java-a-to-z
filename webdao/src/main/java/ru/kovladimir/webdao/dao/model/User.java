package ru.kovladimir.webdao.dao.model;

import java.util.List;
import java.util.Objects;

/**
 * Created by Vladimir on 18.10.2016.
 */
public class User {

    private long id;

    private String login;

    private String password;

    private Role role;

    private Address address;

    private List<MusicType> musicTypeList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<MusicType> getMusicTypeList() {
        return musicTypeList;
    }

    public void setMusicTypeList(List<MusicType> musicTypeList) {
        this.musicTypeList = musicTypeList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(role, user.role) &&
                Objects.equals(address, user.address) &&
                Objects.equals(musicTypeList, user.musicTypeList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, role, address, musicTypeList);
    }
}
