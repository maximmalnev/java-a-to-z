package ru.kovladimir.webdao.dao.persistence;

import ru.kovladimir.webdao.dao.model.Address;
import ru.kovladimir.webdao.dao.model.MusicType;
import ru.kovladimir.webdao.dao.model.Role;
import ru.kovladimir.webdao.dao.model.User;

import java.util.List;

/**
 * Created by Vladimir on 18.10.2016.
 */
public interface UserRepository {

    Role getRole(User user);

    Address getAddress(User user);

    List<MusicType> getMusicTypes(User user);

    void add(User user, Role role, Address address, List<MusicType> types);

    List<User> getUsers(Role role);

    User getUser(Address address);

    List<User> getUsers(MusicType type);

}
