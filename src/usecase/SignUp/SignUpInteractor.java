package usecase.SignUp;

import entity.User;
import usecase.SignUp.SignUpDataAccessInterface;
import entity.User;
import entity.UserFactory;
import entity.UserFactoryInterface;

import java.time.LocalDateTime;

public class SignUpInteractor implements SignUpInputBoundary{
    final SignUpDataAccessInterface userDataAccessObject;
    final SignUpOutputBoundary userPresenter;
    final UserFactoryInterface userFactoryInterface;

    public SignUpInteractor(SignUpDataAccessInterface userSignupDataAccessInterface,
                            SignUpOutputBoundary signupOutputBoundary,
                            UserFactory userFactoryInterface) {
        this.userDataAccessObject = userSignupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userFactoryInterface = userFactoryInterface;
    }

    @Override
    public void execute(SignUpInputData signupInputData) {
        if (userDataAccessObject.existsByName(signupInputData.getUsername())) {
            userPresenter.prepareFailView("User already exists.");
        } else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        } else {
            User user = userFactoryInterface.createUser(signupInputData.getName(), signupInputData.getUsername(), signupInputData.getPassword(), signupInputData.getEmail(), signupInputData.getCourses());
            userDataAccessObject.save(user);

            SignUpOutputData signupOutputData = new SignUpOutputData(user.getName(), false);
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }
}
