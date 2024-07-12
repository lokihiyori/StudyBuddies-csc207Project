
package view;

import javax.swing.*;
import java.awt.*;

public class Go_to_group_Go_to_search_UI extends JFrame {

    private JButton loginButton;
    private JButton signupButton;

    public Go_to_group_Go_to_search_UI() {
        setTitle("Welcome to the App");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI() {
        // Create buttons
        loginButton = new JButton("Go to your group chats");
        signupButton = new JButton("Go to search bar");

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
                Go_to_group_Go_to_search_UI a = new Go_to_group_Go_to_search_UI();
                a.setVisible(true);
            }
        });
    }
}
