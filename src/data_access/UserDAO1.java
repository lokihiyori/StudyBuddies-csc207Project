package data_access;

import entity.User;
import entity.Users;
import java.util.ArrayList;
import java.util.List;

public class UserDAO1 {
    private final List<User> users = new ArrayList<>();
    public UserDAO1() {

        users.add(new Users("John Doe", "password123", "johndoe", "john@example.com", new ArrayList<>(), new ArrayList<>()));
        users.add(new Users("Jane Smith", "password456", "janesmith", "jane@example.com", new ArrayList<>(), new ArrayList<>()));
    }

    public User findByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public void addUser(User user) {
        users.add(user);
    }
}

