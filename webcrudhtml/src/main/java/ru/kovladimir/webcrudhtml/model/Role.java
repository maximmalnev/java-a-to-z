package ru.kovladimir.webcrudhtml.model;

/**
 * Role enum.
 */
public enum Role {
    ADMIN {
        @Override
        public String toString() {
            return "admin";
        }
    },
    USER {
        @Override
        public String toString() {
            return "user";
        }
    };

    public static Role getRole(String name) {
        Role role = null;
        if ("admin".equalsIgnoreCase(name)) {
            role = ADMIN;
        } else if ("user".equalsIgnoreCase(name)) {
            role = USER;
        }
        return role;
    }
}
