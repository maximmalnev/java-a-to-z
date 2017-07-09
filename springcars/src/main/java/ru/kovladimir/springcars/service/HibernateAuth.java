package ru.kovladimir.springcars.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kovladimir.springcars.persistence.models.User;
import ru.kovladimir.springcars.persistence.repository.UserRepository;

import java.util.Collections;

/**
 * Hibernate UserDetailsService.
 */
@Service
public class HibernateAuth implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public HibernateAuth(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * {@inheritDoc}
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.get(username);
        if (user == null) {
            throw new UsernameNotFoundException("Username not found");
        } else {
            return new org.springframework.security.core.userdetails.User(
                    user.getLogin(), user.getPassword(), Collections.emptyList()
            );
        }
    }
}
