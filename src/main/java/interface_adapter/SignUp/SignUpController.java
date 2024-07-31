package interface_adapter.SignUp;


import entity.GroupChat;
import use_case.Signup.SignupInputBoundary;
import use_case.Signup.SignupInputData;

import java.util.List;

public class SignUpController {

    final SignupInputBoundary userSignupUseCaseInteractor;
    public SignUpController(SignupInputBoundary userSignupUseCaseInteractor) {
        this.userSignupUseCaseInteractor = userSignupUseCaseInteractor;
    }

    public void execute(String username, String password, String repeatPassword, String email) {
        SignupInputData signupInputData = new SignupInputData(
                 username, password, repeatPassword, email);

        userSignupUseCaseInteractor.execute(signupInputData);
    }
}
