package use_case.LoginSignup;

/**
 * The LoginSignupOutputBoundary interface defines the method required for handling
 * the output of the user signup process. Implementations of this interface should provide
 * the logic for preparing the view or response when a user successfully signs up or registers.
 */
public interface LoginSignupOutputBoundary {

    /**
     * Prepares the view or response to be shown when the signup process is successful.
     * Implementations should define how the success information, including user data, is presented.
     *
     * @param user the data related to the user who successfully signed up or registered.
     */
    void prepareSuccessView(LoginSignupOutputData user);
}
