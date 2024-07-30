package use_case.LoginSignup;
import data_access.FileUserDataAccessObject;
import entity.User;

public class LoginSignupInteractor implements LoginSignupInputBoundary{
    final LoginSignupOutputBoundary loginSignupOutputBoundary;
    final FileUserDataAccessObject userDataAccessObject;

    public LoginSignupInteractor(LoginSignupOutputBoundary loginSignupOutputBoundary, FileUserDataAccessObject userDataAccessObject) {
        this.loginSignupOutputBoundary = loginSignupOutputBoundary;
        this.userDataAccessObject = userDataAccessObject;
    }

    public void execute(LoginSignupInputData loginSignupInputData) {
        User user = userDataAccessObject.get(loginSignupInputData.getUsername());
        LoginSignupOutputData outputData = new LoginSignupOutputData(user.getName());
        loginSignupOutputBoundary.prepareSuccessView(outputData);
    }
}
