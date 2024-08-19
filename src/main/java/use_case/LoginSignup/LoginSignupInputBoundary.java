package use_case.LoginSignup;

/**
 * The LoginSignupInputBoundary interface defines the method required for handling
 * the process of user signup or registration. Implementations of this interface are
 * responsible for processing the input data related to user signup and executing
 * the necessary operations to create a new user account.
 */
public interface LoginSignupInputBoundary {

    /**
     * Executes the user signup process using the provided input data.
     * Implementations should handle the logic for validating and processing the
     * signup data and creating a new user account in the system.
     *
     * @param loginSignupInputData the data required to perform the signup operation, including credentials.
     */
    void execute(LoginSignupInputData loginSignupInputData);
}
