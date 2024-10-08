package view;

import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * LoginView is a JPanel that provides a user interface for logging into the system.
 */
public class LoginView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "log in";
    private final LoginViewModel loginViewModel;

    private final LoginController loginController;

    /**
     * The username chosen by the user
     */
    final JTextField usernameInputField = new JTextField(15);
    private final JLabel usernameErrorField = new JLabel();
    /**
     * The password
     */
    final JPasswordField passwordInputField = new JPasswordField(15);
    private final JLabel passwordErrorField = new JLabel();

    final JButton logIn;
    final JButton cancel;

    /**
     * Constructs a new LoginView with the specified view model and controller.
     *
     * @param loginViewModel the LoginViewModel instance
     * @param controller     the LoginController instance
     */
    public LoginView(LoginViewModel loginViewModel, LoginController controller) {
        this.loginController = controller;
        this.loginViewModel = loginViewModel;
        this.loginViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Login Screen");
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setForeground(new Color(4, 91, 205));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel("Username"), usernameInputField);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("Password"), passwordInputField);

        JPanel buttons = new JPanel();
        logIn = new JButton(loginViewModel.LOGIN_BUTTON_LABEL);
        logIn.setBackground(new Color(14, 216, 85));
        logIn.setForeground(Color.WHITE);
        logIn.setFont(new Font("Arial", Font.PLAIN, 16));
        buttons.add(logIn);
        cancel = new JButton(loginViewModel.CANCEL_BUTTON_LABEL);
        cancel.setBackground(new Color(39, 174, 96));
        cancel.setForeground(Color.WHITE);
        cancel.setFont(new Font("Arial", Font.PLAIN, 16));
        buttons.add(cancel);

        logIn.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(logIn)) {
                            LoginState currentState = loginViewModel.getState();

                            loginController.execute(
                                    currentState.getUsername(),
                                    currentState.getPassword()
                            );
                        }
                    }
                }
        );
        cancel.addActionListener(e -> handleCancel());;

        usernameInputField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                LoginState currentState = loginViewModel.getState();
                currentState.setUsername(usernameInputField.getText());
                loginViewModel.setState(currentState);

            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        passwordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        LoginState currentState = loginViewModel.getState();
                        currentState.setPassword(passwordInputField.getText() + e.getKeyChar());
                        loginViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(450, 500));
        this.add(Box.createVerticalStrut(35));
        this.add(title);
        this.add(usernameInfo);
        this.add(usernameErrorField);
        this.add(passwordInfo);
        this.add(passwordErrorField);
        this.add(buttons);
    }

    /**
     * Handles the cancel action.
     */
    private void handleCancel() {
        loginController.executeCancel();}

    /**
     * Handles action events.
     *
     * @param evt the ActionEvent object
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    /**
     * Handles property changes in the view model.
     *
     * @param evt the PropertyChangeEvent object
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoginState state = (LoginState) evt.getNewValue();
        setFields(state);
    }

    /**
     * Sets the fields in the view based on the current state.
     *
     * @param state the current LoginState
     */
    private void setFields(LoginState state) {
        usernameInputField.setText(state.getUsername());
    }

    /**
     * Gets the LoginController associated with this view.
     *
     * @return the LoginController instance
     */
    public LoginController getController() {
        return loginController;
    }
}
