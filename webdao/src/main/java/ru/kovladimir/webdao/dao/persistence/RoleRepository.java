package ru.kovladimir.webdao.dao.persistence;

import ru.kovladimir.webdao.dao.model.Role;
import ru.kovladimir.webdao.dao.model.User;

import java.util.List;

/**
 * Created by Vladimir on 19.10.2016.
 */
public interface RoleRepository {

    List<User> getUsers(Role role);

}
