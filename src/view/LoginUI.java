package view;

import interface_adapter.LoginViewModel;
import interface_adapter.LoginState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LoginUI extends JFrame implements ActionListener {

    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final JButton loginButton;
    private final JLabel messageLabel;
    private final LoginViewModel loginViewModel;

    /**
     * A window with username and password fields and a login button.
     */
    public LoginUI() {
        setTitle("Login");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        loginViewModel = new LoginViewModel();
        loginViewModel.addPropertyChangeListener(new LoginViewModelListener());

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton(LoginViewModel.LOGIN_BUTTON_LABEL);
        messageLabel = new JLabel("");

        // Set layout manager
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Add username field
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(usernameField, gbc);

        // Add password field
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(passwordField, gbc);

        // Add login button
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(loginButton, gbc);

        // Add message label
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(messageLabel, gbc);

        loginButton.addActionListener(this);
    }

    /**
     * React to a button click that results in evt.
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            LoginState state = loginViewModel.getState();
            state.setUsername(username);
            state.setPassword(password);

            if (authenticate(username, password)) {
                // Navigate to Main_UI
                Main_UI mainUI = new Main_UI();
                mainUI.setVisible(true);
                this.dispose(); // Close the login window
            } else {
                messageLabel.setText("Invalid username or password.");
            }
        }
    }

    /**
     * Simulates authenticating a user.
     * Replace this method with your actual authentication logic.
     */
    private boolean authenticate(String username, String password) {
        // Simulate authentication logic
        // Replace this with actual authentication logic (e.g., database query)
        // For demonstration, let's assume the account exists if username is "user" and password is "password"
        return "user".equals(username) && "password".equals(password);
    }

    private class LoginViewModelListener implements PropertyChangeListener {
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if ("state".equals(evt.getPropertyName())) {
                LoginState newState = (LoginState) evt.getNewValue();
                usernameField.setText(newState.getUsername());
                passwordField.setText(newState.getPassword());
            }
        }
    }}

