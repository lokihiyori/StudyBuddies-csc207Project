package interface_adapter.login;

import use_case.Cancel.CancelInputBoundary;
import use_case.Login.LoginInputBoundary;
import use_case.Login.LoginInputData;

public class LoginController {
    private final LoginInputBoundary loginUseCaseInteractor;
    private final CancelInputBoundary cancelInteractor;

    public LoginController(LoginInputBoundary loginUseCaseInteractor, CancelInputBoundary cancelInputBoundary) {
        this.loginUseCaseInteractor = loginUseCaseInteractor;
        this.cancelInteractor = cancelInputBoundary;
    }

    public void execute(String username, String password) {
        LoginInputData loginInputData = new LoginInputData(
                username, password);

        loginUseCaseInteractor.execute(loginInputData);
    }
    public void executeCancel(){
        cancelInteractor.execute();
    }

}
