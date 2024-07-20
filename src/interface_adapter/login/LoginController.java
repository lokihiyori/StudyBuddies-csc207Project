package interface_adapter.login;

import use_case.Login.LoginInputBoundary;
import use_case.Login.LoginInputData;

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
