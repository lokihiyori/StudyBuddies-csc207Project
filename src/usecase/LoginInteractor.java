package use_case;

import data_access.UserDAO1;
import entity.User;
import session.SessionManager;
import usecase.LoginInputBoundary;
import usecase.LoginInputData;
import usecase.LoginOutputBoundary;
import usecase.LoginOutputData;

public class LoginInteractor implements LoginInputBoundary {
    private final UserDAO1 userDAO;
    private final LoginOutputBoundary loginOutputBoundary;

    public LoginInteractor(UserDAO1 userDAO, LoginOutputBoundary loginOutputBoundary) {
        this.userDAO = userDAO;
        this.loginOutputBoundary = loginOutputBoundary;
    }

    @Override
    public void login(LoginInputData inputData) {
        User user = userDAO.findByUsername(inputData.getUsername());
        if (user != null && user.getPassword().equals(inputData.getPassword())) {
            SessionManager.login(user);
            loginOutputBoundary.present(new LoginOutputData(true, "Login successful!"));
        } else {
            loginOutputBoundary.present(new LoginOutputData(false, "Invalid username or password."));
        }
    }
}
