package interface_adapter;

import usecase.LoginInputBoundary;
import usecase.LoginInputData;

public class LoginController {
    private final LoginInputBoundary loginInputBoundary;

    public LoginController(LoginInputBoundary loginInputBoundary) {
        this.loginInputBoundary = loginInputBoundary;
    }

    public void login(String username, String password) {
        LoginInputData inputData = new LoginInputData(username, password);
        loginInputBoundary.login(inputData);
    }
}
