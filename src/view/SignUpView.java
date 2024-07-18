package view;

import data_access.UserDAO1;
import entity.Users;
import interface_adapter.LoginController;
import interface_adapter.LoginPresenter;
import interface_adapter.LoginViewModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SignUpView extends JFrame {
    private final UserDAO1 userDAO;

    public SignUpView(UserDAO1 userDAO) {
        this.userDAO = userDAO;
        setupUI();
    }

    private void setupUI() {
        setTitle("Sign Up");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        LabelTextPanel namePanel = new LabelTextPanel("Name:");
        LabelTextPanel usernamePanel = new LabelTextPanel("Username:");
        LabelTextPanel passwordPanel = new LabelTextPanel("Password:");
        LabelTextPanel emailPanel = new LabelTextPanel("Email:");

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String name = namePanel.getText();
                String username = usernamePanel.getText();
                String password = passwordPanel.getText();
                String email = emailPanel.getText();

                Users newUser = new Users(name, username, password, email, new ArrayList<>(), new ArrayList<>());
                userDAO.addUser(newUser);  // Assume this method is implemented to add the user to the DAO
                JOptionPane.showMessageDialog(SignUpView.this, "User registered successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                navigateToLogin();
            }
        });

        panel.add(namePanel);
        panel.add(usernamePanel);
        panel.add(passwordPanel);
        panel.add(emailPanel);
        panel.add(signUpButton);

        add(panel);
    }

    private void navigateToLogin() {
        LoginView loginView = new LoginView(new LoginController(new use_case.LoginInteractor(userDAO, new LoginPresenter(new LoginViewModel()))), new LoginViewModel());
        loginView.setVisible(true);
        this.dispose();
    }
}
