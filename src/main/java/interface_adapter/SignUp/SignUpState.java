package interface_adapter.SignUp;
/**
 * Represents the state of the sign-up process, including user input and validation errors.
 */
public class SignUpState {
    private String username = "";
    private String usernameError = null;
    private String email = "";
    private String emailError = null;
    private String password = "";
    private String passwordError = null;
    private String repeatPassword = "";
    private String repeatPasswordError = null;
    /**
     * Constructs a {@code SignUpState} object with the values copied from another {@code SignUpState} instance.
     *
     * @param copy the {@code SignUpState} instance to copy values from
     */
    public SignUpState(SignUpState copy) {
        username = copy.username;
        usernameError = copy.usernameError;
        email = copy.email;
        emailError = copy.emailError;
        password = copy.password;
        passwordError = copy.passwordError;
        repeatPassword = copy.repeatPassword;
        repeatPasswordError = copy.repeatPasswordError;
    }
    /**
     * Constructs a new {@code SignUpState} object with default values.
     */
    public SignUpState() {
    }

    /**
     * Returns the username entered by the user.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the error message related to the username input.
     *
     * @return the username error message
     */
    public String getUsernameError() {
        return usernameError;
    }

    /**
     * Returns the email address entered by the user.
     *
     * @return the email address
     */
    public String getEmail(){
        return email;
    }

    /**
     * Returns the error message related to the email input.
     *
     * @return the email error message
     */
    public String getEmailError(){
        return emailError;
    }

    /**
     * Returns the password entered by the user.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns the error message related to the password input.
     *
     * @return the password error message
     */
    public String getPasswordError() {
        return passwordError;
    }

    /**
     * Returns the repeated password entered by the user.
     *
     * @return the repeated password
     */
    public String getRepeatPassword() {
        return repeatPassword;
    }

    /**
     * Returns the error message related to the repeated password input.
     *
     * @return the repeated password error message
     */
    public String getRepeatPasswordError() {
        return repeatPasswordError;
    }

    /**
     * Sets the username entered by the user.
     *
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the error message related to the username input.
     *
     * @param usernameError the username error message to set
     */
    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    /**
     * Sets the email address entered by the user.
     *
     * @param email the email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the error message related to the email input.
     *
     * @param emailError the email error message to set
     */
    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    /**
     * Sets the password entered by the user.
     *
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets the error message related to the password input.
     *
     * @param passwordError the password error message to set
     */
    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    /**
     * Sets the repeated password entered by the user.
     *
     * @param repeatPassword the repeated password to set
     */
    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    /**
     * Sets the error message related to the repeated password input.
     *
     * @param repeatPasswordError the repeated password error message to set
     */
    public void setRepeatPasswordError(String repeatPasswordError) {
        this.repeatPasswordError = repeatPasswordError;
    }
}
