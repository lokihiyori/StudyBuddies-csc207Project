package interface_adapter.SignUp;


import usecase.SignUp.SignUpInputBoundary;
import usecase.SignUp.SignUpInputData;

import java.util.List;

public class SignUpController {

    final SignUpInputBoundary userSignupUseCaseInteractor;
    public SignUpController(SignUpInputBoundary userSignupUseCaseInteractor) {
        this.userSignupUseCaseInteractor = userSignupUseCaseInteractor;
    }

    public void execute(String name, String username, String password, String repeatPassword, String email, List<String> courses) {
        SignUpInputData signupInputData = new SignUpInputData(
                name, username, password, repeatPassword, email, courses);

        userSignupUseCaseInteractor.execute(signupInputData);
    }
}
