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

public class SignUpPresenter implements SignupOutputBoundary, LoginSignupOutputBoundary {

    private final SignUpViewModel signupViewModel;
    private final LoginViewModel loginViewModel;
    private ViewManagerModel viewManagerModel;
    private LoginSignupViewModel loginSignupViewModel;

    public SignUpPresenter(ViewManagerModel viewManagerModel,
                           SignUpViewModel signupViewModel,
                           LoginViewModel loginViewModel,
                           LoginSignupViewModel loginSignupViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
        this.loginSignupViewModel = loginSignupViewModel;
    }

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

    @Override
    public void prepareFailView(String error) {
        SignUpState signupState = signupViewModel.getState();
        signupState.setUsernameError(error);
        signupViewModel.firePropertyChanged();
    }

    public void prepareSuccessView(LoginSignupOutputData user) {
        LoginState loginState = loginViewModel.getState();
        loginState.setUsername(user.getUsername());
        this.loginViewModel.setState(loginState);
        this.loginViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(loginViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
