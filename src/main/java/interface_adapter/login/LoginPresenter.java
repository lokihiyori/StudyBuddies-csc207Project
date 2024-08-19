package interface_adapter.login;

import interface_adapter.LoginSignup.LoginSignupViewModel;
import interface_adapter.SignUp.SignUpViewModel;
import interface_adapter.logged_In.LoggedInState;
import interface_adapter.logged_In.LoggedInViewModel;
import interface_adapter.ViewManagerModel;
import use_case.Cancel.CancelOutputBoundary;
import use_case.Login.LoginOutputBoundary;
import use_case.Login.LoginOutputData;


/**
 * Presenter for handling the login process and view transitions.
 * It manages the interaction between the login use case, the login view model,
 * and other related view models. It updates views based on login success or failure.
 */

public class LoginPresenter implements LoginOutputBoundary, CancelOutputBoundary {
    private final LoginViewModel loginViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private ViewManagerModel viewManagerModel;

    private final SignUpViewModel signUpViewModel;
    private final LoginSignupViewModel loginSignupViewModel;

    /**
     * Constructs a new instance of LoginPresenter.
     *
     * @param viewManagerModel the model responsible for managing view transitions
     * @param loggedInViewModel the view model for the logged-in user view
     * @param loginViewModel the view model for the login view
     * @param signUpViewModel the view model for the sign-up view
     * @param loginSignupViewModel the view model for the login/signup view
     */
    public LoginPresenter(ViewManagerModel viewManagerModel,
                          LoggedInViewModel loggedInViewModel,
                          LoginViewModel loginViewModel,
                          SignUpViewModel signUpViewModel,
                          LoginSignupViewModel loginSignupViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
        this.loginViewModel = loginViewModel;
        this.signUpViewModel = signUpViewModel;
        this.loginSignupViewModel = loginSignupViewModel;
    }

    /**
     * Prepares the view to display upon successful login.
     *
     * @param response the data resulting from a successful login operation
     */
    public void prepareSuccessView(LoginOutputData response) {
        // On success, switch to the logged in view.

        LoggedInState loggedInState = loggedInViewModel.getState();
        loggedInState.setUsername(response.getUsername());
        this.loggedInViewModel.setState(loggedInState);
        this.loggedInViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(loggedInViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares the view to display upon a failed login attempt.
     *
     * @param error the error message to be displayed
     */
    @Override
    public void prepareFailView(String error) {
        LoginState loginState = loginViewModel.getState();
        loginState.setUsernameError(error);
        loginViewModel.firePropertyChanged();
    }

    /**
     * Prepares the view to transition to the sign-up view.
     */
    public void prepareSuccessView() {
        this.viewManagerModel.setActiveView(signUpViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

    }
}
