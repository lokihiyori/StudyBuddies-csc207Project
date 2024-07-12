package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Welcome_UI extends JFrame {

    private JButton loginButton;
    private JButton signupButton;

    public Welcome_UI() {
        setTitle("Welcome to the App");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI() {
        // Create buttons
        loginButton = new JButton("Login");
        signupButton = new JButton("Signup");

        // Add action listeners
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open Login UI
                Login_UI loginUI = new Login_UI();
                loginUI.setVisible(true);
                dispose(); // Close the current window
            }
        });

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open Signup UI (assumed to be implemented similarly to Login_UI)
                // For this example, let's assume Signup_UI is implemented similarly to Login_UI
                //Signup_UI signupUI = new Signup_UI();
                //signupUI.setVisible(true);
                dispose(); // Close the current window
            }
        });

        // Set layout manager
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Add login button
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(loginButton, gbc);

        // Add signup button
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(signupButton, gbc);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Welcome_UI welcomeUI = new Welcome_UI();
                welcomeUI.setVisible(true);
            }
        });
    }
}
