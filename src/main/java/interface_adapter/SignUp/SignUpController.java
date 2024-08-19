package interface_adapter.SignUp;


import entity.GroupChat;
import use_case.Cancel.CancelInputBoundary;
import use_case.LoginSignup.LoginSignupInputBoundary;
import use_case.LoginSignup.LoginSignupInputData;
import use_case.Signup.SignupInputBoundary;
import use_case.Signup.SignupInputData;

import java.util.List;

/**
 * Controller for handling user sign-up and login functionality.
 * This class processes user sign-up and login requests and delegates them to the appropriate input boundaries.
 */
public class SignUpController {

    final SignupInputBoundary userSignupUseCaseInteractor;
    final LoginSignupInputBoundary loginInteractor;

    /**
     * Constructs a {@code SignUpController} with the specified input boundaries.
     *
     * @param userSignupUseCaseInteractor the input boundary for user sign-up operations
     * @param loginInteractor the input boundary for login operations
     */
    public SignUpController(SignupInputBoundary userSignupUseCaseInteractor, LoginSignupInputBoundary loginInteractor) {
        this.userSignupUseCaseInteractor = userSignupUseCaseInteractor;
        this.loginInteractor = loginInteractor;
    }
    /**
     * Handles the user sign-up process.
     * This method creates a {@code SignupInputData} object with the provided user information
     * and passes it to the {@code SignupInputBoundary} for processing.
     *
     * @param username the username for the new account
     * @param email the email address for the new account
     * @param password the password for the new account
     * @param repeatPassword the password confirmation for the new account
     */
    public void execute(String username, String email, String password, String repeatPassword) {
        SignupInputData signupInputData = new SignupInputData(
                 username, email, password, repeatPassword);

        userSignupUseCaseInteractor.execute(signupInputData);
    }
    /**
     * Handles the login process.
     * This method creates a {@code LoginSignupInputData} object with the provided username
     * and passes it to the {@code LoginSignupInputBoundary} for processing.
     *
     * @param username the username to log in
     */
    public void executeLogin(String username){
        LoginSignupInputData loginSignupInputData = new LoginSignupInputData(username);
        loginInteractor.execute(loginSignupInputData);
    }
}
