package use_case.Login;

import entity.User;

/**
 * The LoginInteractor class implements the LoginInputBoundary interface
 * and handles the business logic for the login process. It interacts with the
 * data access layer to verify user credentials and communicates with the
 * output boundary to present the result of the login attempt.
 */
public class LoginInteractor implements LoginInputBoundary {
    final LoginUserDataAccessInterface userDataAccessObject;
    final LoginOutputBoundary loginPresenter;

    /**
     * Constructs a new LoginInteractor with the specified data access interface
     * and output boundary.
     *
     * @param userDataAccessInterface the data access interface used to retrieve user information.
     * @param loginOutputBoundary the output boundary to handle the result of the login process.
     */
    public LoginInteractor(LoginUserDataAccessInterface userDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
    }

    /**
     * Executes the login process using the provided input data.
     * The process involves checking if the account exists, verifying the password,
     * and preparing the appropriate view based on the outcome.
     *
     * @param loginInputData the data required to perform the login operation, including credentials.
     */
    @Override
    public void execute(LoginInputData loginInputData) {
        String username = loginInputData.getUsername();
        String password = loginInputData.getPassword();
        if (!userDataAccessObject.existsByName(username)) {
            loginPresenter.prepareFailView(username + ": Account does not exist.");
        } else {
            String pwd = userDataAccessObject.get(username).getPassword();
            if (!password.equals(pwd)) {
                loginPresenter.prepareFailView("Incorrect password for " + username + ".");
            } else {

                User user = userDataAccessObject.get(loginInputData.getUsername());

                LoginOutputData loginOutputData = new LoginOutputData(user.getName(), false);
                loginPresenter.prepareSuccessView(loginOutputData);
            }
        }
    }

    /**
     * Returns the data access object associated with this interactor.
     *
     * @return the LoginUserDataAccessInterface instance used by this interactor.
     */
    public Object getUserDataAccessObject() {
        return userDataAccessObject;
    }

    /**
     * Returns the output boundary associated with this interactor.
     *
     * @return the LoginOutputBoundary instance used by this interactor.
     */
    public Object getOutputBoundary() {
        return loginPresenter;
    }
}