package use_case.Signup;

/**
 * The SignupOutputBoundary interface defines the methods for presenting the results of the signup process.
 * It provides methods for handling success and failure scenarios during user signup.
 */
public interface SignupOutputBoundary {

    /**
     * Prepares the view for a successful signup.
     * This method is called when the user signup process is completed successfully.
     *
     * @param user the output data containing details of the successfully signed-up user.
     */
    void prepareSuccessView(SignupOutputData user);

    /**
     * Prepares the view for a failed signup.
     * This method is called when there is an error or validation failure during the signup process.
     *
     * @param error a string describing the error or reason for the failure.
     */
    void prepareFailView(String error);
}