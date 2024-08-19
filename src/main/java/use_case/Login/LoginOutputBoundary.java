package use_case.Login;

/**
 * The LoginOutputBoundary interface defines the methods required for handling
 * the output of the login process. Implementations of this interface should provide
 * the logic for preparing views or responses for both successful and failed login attempts.
 */
public interface LoginOutputBoundary {

    /**
     * Prepares the view or response to be shown when the login process is successful.
     * Implementations should define how the success information, including user data, is presented.
     *
     * @param user the data related to the user who successfully logged in.
     */
    void prepareSuccessView(LoginOutputData user);

    /**
     * Prepares the view or response to be shown when the login process fails.
     * Implementations should define how the error message is presented.
     *
     * @param error the error message explaining why the login attempt failed.
     */
    void prepareFailView(String error);
}
