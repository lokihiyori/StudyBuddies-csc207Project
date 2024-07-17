package data_access;

import entity.User;
import entity.Users;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private final List<User> users = new ArrayList<>();

    // Constructor to add some sample users for testing
    public UserDAO() {
        users.add(new Users("John Doe", "password123", "johndoe", "john@example.com", new ArrayList<>(), new ArrayList<>()));
        users.add(new Users("Ana Ciur", "password456", "anaciur", "ana@example.com", new ArrayList<>(), new ArrayList<>()));
    }

    public User findByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null; // Return null if user not found
    }

    // Other methods to add users, remove users, etc.
}
