package use_case.Signup;

/**
 * The SignupInputData class represents the data required for user signup.
 * It includes details such as username, email, password, and password confirmation.
 */
public class SignupInputData {

    /**
     * Constructs a new SignupInputData instance with the specified details.
     *
     * @param username       the username chosen by the user.
     * @param email          the email address of the user.
     * @param password       the password chosen by the user.
     * @param repeatPassword the password repeated for confirmation.
     */
    final private String username;
    private final String email;
    final private String password;
    final private String repeatPassword;

    public SignupInputData(String username, String email, String password, String repeatPassword) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

    /**
     * Gets the username.
     *
     * @return the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the email address.
     *
     * @return the email address.
     */
    public String getEmail() {return email;}

    /**
     * Gets the password.
     *
     * @return the password.
     */
    String getPassword() {
        return password;
    }

    /**
     * Gets the repeated password for confirmation.
     *
     * @return the repeated password.
     */
    public String getRepeatPassword() {
        return repeatPassword;
    }

}
