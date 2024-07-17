package view;

import interface_adapter.LoginController;
import interface_adapter.LoginState;
import interface_adapter.LoginViewModel;
import session.SessionManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    private final LoginController loginController;
    private final LoginViewModel loginViewModel;

    public LoginView(LoginController loginController, LoginViewModel loginViewModel) {
        this.loginController = loginController;
        this.loginViewModel = loginViewModel;
        setupUI();
    }

    private void setupUI() {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        LabelTextPanel usernamePanel = new LabelTextPanel("Username:");
        LabelTextPanel passwordPanel = new LabelTextPanel("Password:");

        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernamePanel.getText();
                String password = passwordPanel.getText();
                loginController.login(username, password);

                LoginState state = loginViewModel.getState();
                JOptionPane.showMessageDialog(LoginView.this, state.getMessage(), state.getStatus(), JOptionPane.INFORMATION_MESSAGE);

                if (SessionManager.isLoggedIn()) {
                    System.out.println("Logged in user: " + SessionManager.getLoggedInUser().getUsername());
                }
            }
        });

        panel.add(usernamePanel);
        panel.add(passwordPanel);
        panel.add(loginButton);

        add(panel);
    }
}
