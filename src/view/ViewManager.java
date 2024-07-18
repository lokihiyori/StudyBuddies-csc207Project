package view;

import interface_adapter.LoginController;
import interface_adapter.LoginPresenter;
import interface_adapter.LoginViewModel;
import use_case.LoginInteractor;
import data_access.UserDAO1;

public class ViewManager {
    public static void main(String[] args) {
        UserDAO1 userDAO = new UserDAO1();
        LoginViewModel loginViewModel = new LoginViewModel();
        LoginPresenter loginPresenter = new LoginPresenter(loginViewModel);
        LoginInteractor loginInteractor = new LoginInteractor(userDAO, loginPresenter);
        LoginController loginController = new LoginController(loginInteractor);

        LoginView loginView = new LoginView(loginController, loginViewModel);
        loginView.setVisible(true);
    }
}
