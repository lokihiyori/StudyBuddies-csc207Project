package use_case.Signup;

/**
 * The SignupInputBoundary interface defines the contract for executing the signup process.
 * It specifies a method to handle user signup input data and perform the necessary operations
 * for user registration.
 */
public interface SignupInputBoundary {

    /**
     * Executes the signup process using the provided input data.
     *
     * @param signupInputData the data required to complete the signup process, including user details.
     */
    void execute(SignupInputData signupInputData);
}
