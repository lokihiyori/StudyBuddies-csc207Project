package interface_adapter.SignUp;


import entity.GroupChat;
import use_case.Cancel.CancelInputBoundary;
import use_case.LoginSignup.LoginSignupInputBoundary;
import use_case.LoginSignup.LoginSignupInputData;
import use_case.Signup.SignupInputBoundary;
import use_case.Signup.SignupInputData;

import java.util.List;

public class SignUpController {

    final SignupInputBoundary userSignupUseCaseInteractor;
    final LoginSignupInputBoundary loginInteractor;
    public SignUpController(SignupInputBoundary userSignupUseCaseInteractor, LoginSignupInputBoundary loginInteractor) {
        this.userSignupUseCaseInteractor = userSignupUseCaseInteractor;
        this.loginInteractor = loginInteractor;
    }

    public void execute(String username, String password, String repeatPassword, String email) {
        SignupInputData signupInputData = new SignupInputData(
                 username, password, repeatPassword, email);

        userSignupUseCaseInteractor.execute(signupInputData);
    }
    public void executeLogin(String username){
        LoginSignupInputData loginSignupInputData = new LoginSignupInputData(username);
        loginInteractor.execute(loginSignupInputData);
    }
}
