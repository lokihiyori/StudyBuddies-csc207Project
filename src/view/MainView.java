package view;

import data_access.UserDAO1;
import interface_adapter.LoginController;
import interface_adapter.LoginPresenter;
import interface_adapter.LoginViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainView extends JFrame {
    private final LoginController loginController;
    private final LoginViewModel loginViewModel;
    private final UserDAO1 userDAO;

    public MainView(LoginController loginController, LoginViewModel loginViewModel, UserDAO1 userDAO) {
        this.loginController = loginController;
        this.loginViewModel = loginViewModel;
        this.userDAO = userDAO;
        setupUI();
    }

    private void setupUI() {
        setTitle("Welcome");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                navigateToLogin();
            }
        });

        JButton signupButton = new JButton("Sign Up");
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                navigateToSignUp();
            }
        });

        panel.add(loginButton);
        panel.add(signupButton);

        add(panel);
    }

    private void navigateToLogin() {
        LoginView loginView = new LoginView(loginController, loginViewModel);
        loginView.setVisible(true);
        this.dispose();
    }

    private void navigateToSignUp() {
        SignUpView signUpView = new SignUpView(userDAO);
        signUpView.setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
        UserDAO1 userDAO = new UserDAO1();
        LoginViewModel loginViewModel = new LoginViewModel();
        LoginPresenter loginPresenter = new LoginPresenter(loginViewModel);
        use_case.LoginInteractor loginInteractor = new use_case.LoginInteractor(userDAO, loginPresenter);
        LoginController loginController = new LoginController(loginInteractor);

        MainView mainView = new MainView(loginController, loginViewModel, userDAO);
        mainView.setVisible(true);
    }
}
