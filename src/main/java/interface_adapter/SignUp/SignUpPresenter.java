package interface_adapter.SignUp;

import interface_adapter.LoginSignup.LoginSignupViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.ViewManagerModel;
import use_case.LoginSignup.LoginSignupInputData;
import use_case.LoginSignup.LoginSignupOutputBoundary;
import use_case.LoginSignup.LoginSignupOutputData;
import use_case.Signup.SignupOutputBoundary;
import use_case.Signup.SignupOutputData;
/**
 * Presenter for handling user sign-up and login functionality.
 * This class prepares the view models for success and failure scenarios during sign-up and login processes.
 */
public class SignUpPresenter implements SignupOutputBoundary, LoginSignupOutputBoundary {

    private final SignUpViewModel signupViewModel;
    private final LoginViewModel loginViewModel;
    private ViewManagerModel viewManagerModel;
    private LoginSignupViewModel loginSignupViewModel;

    /**
     * Constructs a {@code SignUpPresenter} with the specified view models and view manager.
     *
     * @param viewManagerModel the view manager model to switch between views
     * @param signupViewModel the view model for the sign-up view
     * @param loginViewModel the view model for the login view
     * @param loginSignupViewModel the view model for the login-signup view
     */
    public SignUpPresenter(ViewManagerModel viewManagerModel,
                           SignUpViewModel signupViewModel,
                           LoginViewModel loginViewModel,
                           LoginSignupViewModel loginSignupViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
        this.loginSignupViewModel = loginSignupViewModel;
    }

    /**
     * Prepares the view model for a successful sign-up.
     * This method updates the login view model with the username from the sign-up response,
     * then switches the view to the login view.
     *
     * @param response the data containing the username from the sign-up process
     */
    @Override
    public void prepareSuccessView(SignupOutputData response) {
        // On success, switch to the login view.

        SignUpState signupState = signupViewModel.getState();
        LoginState loginState = loginViewModel.getState();
        loginState.setUsername(response.getUsername());
        this.loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares the view model for a failed sign-up.
     * This method sets the error message in the sign-up view model.
     *
     * @param error the error message describing the failure
     */
    @Override
    public void prepareFailView(String error) {
        SignUpState signupState = signupViewModel.getState();
        signupState.setUsernameError(error);
        signupViewModel.firePropertyChanged();
    }

    /**
     * Prepares the view model for a successful login after sign-up.
     * This method updates the login view model with the username from the login-signup response,
     * then switches the view to the login view.
     *
     * @param user the data containing the username from the login-signup process
     */
    public void prepareSuccessView(LoginSignupOutputData user) {
        LoginState loginState = loginViewModel.getState();
        loginState.setUsername(user.getUsername());
        this.loginViewModel.setState(loginState);
        this.loginViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(loginViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
