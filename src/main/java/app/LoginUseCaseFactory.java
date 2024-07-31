package app;

import data_access.FileUserDataAccessObject;
import entity.CommonUserFactory;
import entity.UserFactory;
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
import use_case.Login.LoginUserDataAccessInterface;
import view.LoginView;

import javax.swing.*;
import java.io.IOException;

public class LoginUseCaseFactory {

    /** Prevent instantiation. */
    private LoginUseCaseFactory() {}

    public static LoginView create(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel,
            FileUserDataAccessObject userDataAccessObject,
            SignUpViewModel signUpViewModel) {

        try {
            LoginController loginController = createLoginUseCase(viewManagerModel, loginViewModel, loggedInViewModel, userDataAccessObject, signUpViewModel);
            return new LoginView(loginViewModel, loginController);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Could not open user data file.");
        }

        return null;
    }

    private static LoginController createLoginUseCase(
            ViewManagerModel viewManagerModel,
            LoginViewModel loginViewModel,
            LoggedInViewModel loggedInViewModel,
            FileUserDataAccessObject userDataAccessObject,
            SignUpViewModel signUpViewModel) throws IOException {

        // Notice how we pass this method's parameters to the Presenter.
        LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel, loggedInViewModel, loginViewModel, signUpViewModel);

        UserFactory userFactory = new CommonUserFactory();
        LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);
        CancelOutputBoundary cancelOutputBoundary = new LoginPresenter(viewManagerModel
                , loggedInViewModel, loginViewModel, signUpViewModel);
        CancelInputBoundary cancelInputBoundary = new CancelInteractor(cancelOutputBoundary);
        return new LoginController(loginInteractor, cancelInputBoundary);
    }
}