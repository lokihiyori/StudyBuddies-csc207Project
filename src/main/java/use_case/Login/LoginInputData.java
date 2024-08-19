package use_case.Login;

/**
 * The LoginInputData class is a data structure that holds the necessary
 * information required for a user to log in. This includes the username
 * and password provided by the user.
 */
public class LoginInputData {
    private final String username;
    private final String password;

    /**
     * Constructs a new LoginInputData instance with the specified username
     * and password.
     *
     * @param username the username of the user attempting to log in.
     * @param password the password of the user attempting to log in.
     */
    public LoginInputData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Returns the username of the user attempting to log in.
     *
     * @return the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the password of the user attempting to log in.
     *
     * @return the password.
     */
    public String getPassword() {
        return password;
    }
}
