package view;

import javax.swing.*;
import java.awt.*;

public class Login_UI extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton signupButton;

    public Login_UI() {
        setTitle("Welcome to the App");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI() {
        // Create text fields
        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);

        // Create buttons
        loginButton = new JButton("Login");
        signupButton = new JButton("Signup");

        // Set layout manager
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Add username label and text field
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Username:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(usernameField, gbc);

        // Add password label and text field
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(passwordField, gbc);

        // Add login button
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 2;
        add(loginButton, gbc);

        // Add signup button
        gbc.gridy = 3;
        add(signupButton, gbc);

        // Debug output
        System.out.println("Username field is focusable: " + usernameField.isFocusable());
        System.out.println("Password field is focusable: " + passwordField.isFocusable());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Login_UI loginUI = new Login_UI();
                loginUI.setVisible(true);
            }
        });
    }
}
