package interface_adapter.SignUp;


import entity.GroupChat;
import usecase.SignUp.SignUpInputBoundary;
import usecase.SignUp.SignUpInputData;

import java.util.List;

public class SignUpController {

    final SignUpInputBoundary userSignupUseCaseInteractor;
    public SignUpController(SignUpInputBoundary userSignupUseCaseInteractor) {
        this.userSignupUseCaseInteractor = userSignupUseCaseInteractor;
    }

    public void execute(String name, String username, String password, String repeatPassword, String email, List<String> courses, List<GroupChat> groupChatList) {
        SignUpInputData signupInputData = new SignUpInputData(
                name, username, password, repeatPassword, email, courses, groupChatList);

        userSignupUseCaseInteractor.execute(signupInputData);
    }
}
