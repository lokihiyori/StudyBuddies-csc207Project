package interface_adapter.login;

/**
 * Represents the state of the login view, including user credentials and error messages.
 * This class holds the username, password, and any associated errors that may occur during the login process.
 */
public class LoginState {
    private String username = "";
    private String usernameError = null;
    private String password = "";
    private String passwordError = null;

    /**
     * Constructs a new instance of LoginState by copying the provided instance.
     *
     * @param copy the LoginState instance to copy
     */
    public LoginState(LoginState copy) {
        username = copy.username;
        usernameError = copy.usernameError;
        password = copy.password;
        passwordError = copy.passwordError;
    }


    /**
     * Constructs a new, empty instance of LoginState.
     */
    // Because of the previous copy constructor, the default constructor must be explicit.
    public LoginState() {}

    /**
     * Gets the username.
     *
     * @return the username
     */

    public String getUsername() {
        return username;
    }

    /**
     * Gets the username error message.
     *
     * @return the username error message, or null if no error
     */
    public String getUsernameError() {
        return usernameError;
    }

    /**
     * Gets the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the password error message.
     *
     * @return the password error message, or null if no error
     */
    public String getPasswordError() {
        return passwordError;
    }

    /**
     * Sets the username.
     *
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the username error message.
     *
     * @param usernameError the error message to set
     */
    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    /**
     * Sets the password.
     *
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the password error message.
     *
     * @param passwordError the error message to set
     */
    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }
}