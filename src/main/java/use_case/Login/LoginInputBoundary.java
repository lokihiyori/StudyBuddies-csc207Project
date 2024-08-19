package use_case.Login;

/**
 * The LoginInputBoundary interface defines the method required for handling
 * the login process. Implementations of this interface are responsible for
 * processing the login input data and executing the necessary operations
 * to authenticate a user.
 */
public interface LoginInputBoundary {

    /**
     * Executes the login process using the provided input data.
     * Implementations should handle the logic for validating and processing the
     * login credentials and authenticating the user.
     *
     * @param loginInputData the data required to perform the login operation, including credentials.
     */
    void execute(LoginInputData loginInputData);
}
