package interface_adapter;

import usecase.LoginOutputBoundary;
import usecase.LoginOutputData;
import session.SessionManager;

public class LoginPresenter implements LoginOutputBoundary {
    private final LoginViewModel loginViewModel;

    public LoginPresenter(LoginViewModel loginViewModel) {
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void present(LoginOutputData outputData) {
        if (outputData.isSuccess()) {
            loginViewModel.setState(new LoginState("Success", outputData.getMessage()));
            System.out.println("User logged in: " + SessionManager.getLoggedInUser().getUsername());
        } else {
            loginViewModel.setState(new LoginState("Failure", outputData.getMessage()));
        }
    }
}
