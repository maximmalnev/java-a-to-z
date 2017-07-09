package ru.kovladimir.user;

/**
 * Validation.
 */
public class Validation implements Validate{

    /**
     * Is user valid.
     * @param user User.
     * @return areValid.
     */
    public boolean isValid(User user) {
        return user.getId() > 0 && user.getName() != null && user.getAge() >= 0;
    }

}
