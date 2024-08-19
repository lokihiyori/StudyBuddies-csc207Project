package use_case.LoginSignup;
import data_access.FileUserDataAccessObject;
import entity.User;

/**
 * The LoginSignupInteractor class implements the LoginSignupInputBoundary interface
 * and handles the business logic for the user signup process. It interacts with the
 * data access object to retrieve user information and communicates with the
 * output boundary to present the result of the signup process.
 */
public class LoginSignupInteractor implements LoginSignupInputBoundary{
    final LoginSignupOutputBoundary loginSignupOutputBoundary;
    final FileUserDataAccessObject userDataAccessObject;

    /**
     * Constructs a new LoginSignupInteractor with the specified output boundary
     * and data access object.
     *
     * @param loginSignupOutputBoundary the output boundary to handle the result of the signup process.
     * @param userDataAccessObject the data access object used to retrieve user information.
     */
    public LoginSignupInteractor(LoginSignupOutputBoundary loginSignupOutputBoundary, FileUserDataAccessObject userDataAccessObject) {
        this.loginSignupOutputBoundary = loginSignupOutputBoundary;
        this.userDataAccessObject = userDataAccessObject;
    }

    /**
     * Executes the user signup process using the provided input data.
     * The process involves retrieving user information based on the provided username
     * and preparing the success view through the output boundary.
     *
     * @param loginSignupInputData the data required to perform the signup operation.
     */
    @Override
    public void execute(LoginSignupInputData loginSignupInputData) {
        User user = userDataAccessObject.get(loginSignupInputData.getUsername());
        LoginSignupOutputData outputData = new LoginSignupOutputData(user.getName());
        loginSignupOutputBoundary.prepareSuccessView(outputData);
    }
}
