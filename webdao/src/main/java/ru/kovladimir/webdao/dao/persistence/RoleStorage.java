package ru.kovladimir.webdao.dao.persistence;

import ru.kovladimir.webdao.dao.model.Role;
import ru.kovladimir.webdao.dao.model.User;

import java.util.List;

/**
 * Created by Vladimir on 19.10.2016.
 */
public class RoleStorage implements DAO<Role>, RoleRepository {
    @Override
    public List<Role> getAll() {
        return null;
    }

    @Override
    public Role get(long id) {
        return null;
    }

    @Override
    public void add(Role element) {

    }

    @Override
    public void update(Role element) {

    }

    @Override
    public void delete(Role element) {

    }

    @Override
    public List<User> getUsers(Role role) {
        return null;
    }
}
