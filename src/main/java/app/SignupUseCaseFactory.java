package app;
import interface_adapter.LoginSignup.LoginSignupViewModel;
import interface_adapter.SignUp.*;
import data_access.FileUserDataAccessObject;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import use_case.LoginSignup.LoginSignupInputBoundary;
import use_case.LoginSignup.LoginSignupInteractor;
import use_case.LoginSignup.LoginSignupOutputBoundary;
import use_case.Signup.SignupUserDataAccessInterface;
import entity.CommonUserFactory;
import entity.UserFactory;
import use_case.Signup.SignupInputBoundary;
import use_case.Signup.SignupInteractor;
import use_case.Signup.SignupOutputBoundary;
import view.SignupView;

import javax.swing.*;
import java.io.IOException;

public class SignupUseCaseFactory {

    /** Prevent instantiation. */
    private SignupUseCaseFactory() {}

    public static SignupView create(ViewManagerModel viewManagerModel, LoginViewModel loginViewModel, SignUpViewModel signupViewModel, FileUserDataAccessObject userDataAccessObject, LoginSignupViewModel loginSignupViewModel) {

        try {
            SignUpController signupController = createUserSignupUseCase(viewManagerModel, signupViewModel, loginViewModel, userDataAccessObject, loginSignupViewModel);
            return new SignupView(signupController, signupViewModel);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static SignUpController createUserSignupUseCase(ViewManagerModel viewManagerModel, SignUpViewModel signupViewModel,
                                                            LoginViewModel loginViewModel, FileUserDataAccessObject userDataAccessObject,
                                                            LoginSignupViewModel loginSignupViewModel) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        SignupOutputBoundary signupOutputBoundary = new SignUpPresenter(viewManagerModel, signupViewModel, loginViewModel, loginSignupViewModel);

        UserFactory userFactory = new CommonUserFactory();

        SignupInputBoundary userSignupInteractor = new SignupInteractor(
                (SignupUserDataAccessInterface) userDataAccessObject, signupOutputBoundary, userFactory);
        LoginSignupOutputBoundary loginSignupOutputBoundary = new SignUpPresenter(viewManagerModel, signupViewModel, loginViewModel, loginSignupViewModel);
        LoginSignupInputBoundary loginSignupInteractor = new LoginSignupInteractor(loginSignupOutputBoundary, userDataAccessObject);

        return new SignUpController(userSignupInteractor, loginSignupInteractor);
    }
}
