package session;

import entity.User;

public class SessionManager {
    private static User loggedInUser;

    public static void login(User user) {
        loggedInUser = user;
    }

    public static void logout() {
        loggedInUser = null;
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static boolean isLoggedIn() {
        return loggedInUser != null;
    }
}
