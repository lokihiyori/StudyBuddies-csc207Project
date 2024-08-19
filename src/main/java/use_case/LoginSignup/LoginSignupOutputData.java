package use_case.LoginSignup;

/**
 * The LoginSignupOutputData class represents the output data for a user signup process.
 * It contains information related to the user who has successfully signed up or registered.
 */
public class LoginSignupOutputData {
    private final String username;

    /**
     * Constructs a new LoginSignupOutputData instance with the specified username.
     *
     * @param username the username of the user who successfully signed up or registered.
     */
    public LoginSignupOutputData(String username) {
        this.username = username;
    }

    /**
     * Returns the username of the user who successfully signed up or registered.
     *
     * @return the username.
     */
    public String getUsername() {
        return username;
    }
}
