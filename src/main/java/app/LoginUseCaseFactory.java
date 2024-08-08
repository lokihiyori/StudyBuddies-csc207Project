package app;

import data_access.FileUserDataAccessObject;
import entity.CommonUserFactory;
import entity.UserFactory;
import interface_adapter.LoginSignup.LoginSignupViewModel;
import interface_adapter.SignUp.SignUpViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_In.LoggedInViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import use_case.Cancel.CancelInputBoundary;
import use_case.Cancel.CancelInteractor;
import use_case.Cancel.CancelOutputBoundary;
import use_case.Login.LoginInputBoundary;
import use_case.Login.LoginInteractor;
import use_case.Login.LoginOutputBoundary;
import view.LoginView;

import javax.swing.*;
import java.io.IOException;

/**
 * Factory class for creating instances of LoginView and associated controllers and use cases.
 */
public class LoginUseCaseFactory {

    /** Prevent instantiation. */
    private LoginUseCaseFactory() {}

    /**
     * Creates a new LoginView along with its controller and initializes the required use cases.
     *
     * @param viewManagerModel    the ViewManagerModel instance
     * @param loginViewModel      the LoginViewModel instance
     * @param loggedInViewModel   the LoggedInViewModel instance
     * @param userDataAccessObject the FileUserDataAccessObject instance
     * @param signUpViewModel     the SignUpViewModel instance
     * @param loginSignupViewModel the LoginSignupViewModel instance
     * @return a new LoginView instance
     */
    public static LoginView create(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel,
            FileUserDataAccessObject userDataAccessObject,
            SignUpViewModel signUpViewModel,
            LoginSignupViewModel loginSignupViewModel) {

        try {
            LoginController loginController = createLoginUseCase(viewManagerModel,
                    loginViewModel, loggedInViewModel, userDataAccessObject,
                    signUpViewModel, loginSignupViewModel);
            return new LoginView(loginViewModel, loginController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    /**
     * Creates and initializes the required use cases and controller for logging in.
     *
     * @param viewManagerModel    the ViewManagerModel instance
     * @param loginViewModel      the LoginViewModel instance
     * @param loggedInViewModel   the LoggedInViewModel instance
     * @param userDataAccessObject the FileUserDataAccessObject instance
     * @param signUpViewModel     the SignUpViewModel instance
     * @param loginSignupViewModel the LoginSignupViewModel instance
     * @return a new LoginController instance
     * @throws IOException if there is an error accessing the user data file
     */
    private static LoginController createLoginUseCase(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel,
            FileUserDataAccessObject userDataAccessObject,
            SignUpViewModel signUpViewModel, LoginSignupViewModel loginSignupViewModel) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, loggedInViewModel,
                loginViewModel, signUpViewModel, loginSignupViewModel);

        UserFactory userFactory = new CommonUserFactory();
        LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);
        CancelOutputBoundary cancelOutputBoundary = new LoginPresenter(viewManagerModel
                , loggedInViewModel, loginViewModel, signUpViewModel, loginSignupViewModel);
        CancelInputBoundary cancelInputBoundary = new CancelInteractor(cancelOutputBoundary);
        return new LoginController(loginInteractor, cancelInputBoundary);
    }
}