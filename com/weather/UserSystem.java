package com.weather;

import java.util.HashMap;
import java.util.Map;

public class UserSystem {
    private Map<String, User> users;

    public UserSystem() {
        users = new HashMap<>();
    }

    public void addUser(String username, String password) {
        User user = new User(username, password);
        users.put(username, user);
    }

    public boolean authenticateUser(String username, String password) {
        User user = users.get(username);
        return user != null && user.getPassword().equals(password);
    }

    public void setUserEmail(String username, String email) {
        User user = users.get(username);
        if (user != null) {
            user.setEmail(email);
        }
    }

    private class User {
        private String username;
        private String password;
        private String email;

        public User(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}
