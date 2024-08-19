package use_case.LoginSignup;

/**
 * The LoginSignupInputData class is a data structure that holds the necessary
 * information required for a user to sign up or register. This currently includes
 * the username provided by the user during the signup process.
 */
public class LoginSignupInputData {
    final private String username;

    /**
     * Constructs a new LoginSignupInputData instance with the specified username.
     *
     * @param username the username provided by the user during the signup process.
     */
    public LoginSignupInputData(String username) {this.username = username;}

    /**
     * Returns the username provided by the user during the signup process.
     *
     * @return the username.
     */
    public String getUsername(){return username;}
}
