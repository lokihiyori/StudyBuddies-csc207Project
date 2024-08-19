package interface_adapter.login;

import use_case.Cancel.CancelInputBoundary;
import use_case.Login.LoginInputBoundary;
import use_case.Login.LoginInputData;

/**
 * Controller for managing login operations and cancellation actions.
 * It handles user login requests and cancellation requests, delegating the actual work
 * to the appropriate use case interactors.
 */
public class LoginController {
    private final LoginInputBoundary loginUseCaseInteractor;
    private final CancelInputBoundary cancelInteractor;
    /**
     * Constructs a new instance of LoginController.
     *
     * @param loginUseCaseInteractor the interactor responsible for handling login operations
     * @param cancelInputBoundary the interactor responsible for handling cancellation operations
     */
    public LoginController(LoginInputBoundary loginUseCaseInteractor, CancelInputBoundary cancelInputBoundary) {
        this.loginUseCaseInteractor = loginUseCaseInteractor;
        this.cancelInteractor = cancelInputBoundary;
    }

    /**
     * Executes the login operation with the provided username and password.
     *
     * @param username the username of the user trying to log in
     * @param password the password of the user trying to log in
     */
    public void execute(String username, String password) {
        LoginInputData loginInputData = new LoginInputData(
                username, password);

        loginUseCaseInteractor.execute(loginInputData);
    }
    /**
     * Executes the cancel operation.
     * Typically used to cancel the current operation or process.
     */
    public void executeCancel(){
        cancelInteractor.execute();
    }

    /**
     * Gets the LoginInputBoundary instance used by this controller.
     *
     * @return the LoginInputBoundary instance
     */
    public Object getLoginInputBoundary() {
        return loginUseCaseInteractor;
    }

    /**
     * Gets the CancelInputBoundary instance used by this controller.
     *
     * @return the CancelInputBoundary instance
     */
    public Object getCancelInputBoundary() {
        return cancelInteractor;
    }
}
