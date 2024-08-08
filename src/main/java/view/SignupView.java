package view;

import interface_adapter.SignUp.SignUpController;
import interface_adapter.SignUp.SignUpViewModel;
import interface_adapter.SignUp.SignUpController;
import interface_adapter.SignUp.SignUpState;
import interface_adapter.SignUp.SignUpViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SignupView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "sign up";

    private final SignUpViewModel signupViewModel;
    private final JTextField usernameInputField = new JTextField(15);
    private final JTextField emailInputField = new JTextField(15);
    private final JPasswordField passwordInputField = new JPasswordField(15);
    private final JPasswordField repeatPasswordInputField = new JPasswordField(15);
    private final SignUpController signupController;

    private final JButton signUp;
    private final JButton login;

    public SignupView(SignUpController controller, SignUpViewModel signupViewModel) {

        this.signupController = controller;
        this.signupViewModel = signupViewModel;
        signupViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(signupViewModel.TITLE_LABEL);
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setForeground(new Color(4, 91, 205));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel(signupViewModel.USERNAME_LABEL), usernameInputField);
        usernameInfo.setFont(new Font("Arial", Font.PLAIN, 20));
        LabelTextPanel emailInfo = new LabelTextPanel(
                new JLabel(signupViewModel.EMAIL_LABEL), emailInputField);
        usernameInfo.setFont(new Font("Arial", Font.PLAIN, 20));
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel(signupViewModel.PASSWORD_LABEL), passwordInputField);
        usernameInfo.setFont(new Font("Arial", Font.PLAIN, 20));
        LabelTextPanel repeatPasswordInfo = new LabelTextPanel(
                new JLabel(signupViewModel.REPEAT_PASSWORD_LABEL), repeatPasswordInputField);
        usernameInfo.setFont(new Font("Arial", Font.PLAIN, 20));

        JPanel buttons = new JPanel();
        signUp = new JButton(signupViewModel.SIGNUP_BUTTON_LABEL);
        signUp.setBackground(new Color(39, 174, 96));
        signUp.setForeground(Color.WHITE);
        signUp.setFont(new Font("Arial", Font.PLAIN, 16));
        buttons.add(signUp);
        login = new JButton(signupViewModel.LOGIN_BUTTON_LABEL);
        login.setBackground(new Color(192, 57, 43));
        login.setForeground(Color.WHITE);
        login.setFont(new Font("Arial", Font.PLAIN, 16));
        buttons.add(login);

        signUp.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(signUp)) {
                            signupController.execute( signupViewModel.getState().getUsername(),signupViewModel.getState().getEmail(),
                                    String.valueOf( signupViewModel.getState().getPassword()),
                                    String.valueOf(signupViewModel.getState().getRepeatPassword()));}

                    }
                });
        login.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(login)) {
                            SignUpState currentState = signupViewModel.getState();
                            try {
                                signupController.executeLogin(currentState.getUsername());
                            }catch (NullPointerException exception) {
                                signupController.execute(
                                        currentState.getUsername(),
                                        currentState.getEmail(),
                                        currentState.getPassword(),
                                        currentState.getRepeatPassword()
                                );
                            }
                        }
                    }
                }
        );


        // This makes a new KeyListener implementing class, instantiates it, and
        // makes it listen to keystrokes in the usernameInputField.
        //
        // Notice how it has access to instance variables in the enclosing class!
        passwordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignUpState currentState = signupViewModel.getState();
                        currentState.setPassword(passwordInputField.getText() + e.getKeyChar());
                        signupViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        repeatPasswordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignUpState currentState = signupViewModel.getState();
                        currentState.setRepeatPassword(repeatPasswordInputField.getText() + e.getKeyChar());
                        signupViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });


        usernameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignUpState currentState = signupViewModel.getState();
                        currentState.setUsername(usernameInputField.getText() + e.getKeyChar());
                        signupViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        emailInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SignUpState currentState = signupViewModel.getState();
                        currentState.setEmail(emailInputField.getText() + e.getKeyChar());
                        signupViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setPreferredSize(new Dimension(500, 550));;
        this.add(title);
        this.add(Box.createVerticalStrut(35));
        this.add(usernameInfo);
        this.add(emailInfo);
        this.add(passwordInfo);
        this.add(repeatPasswordInfo);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Cancel not implemented yet.");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SignUpState state = (SignUpState) evt.getNewValue();
        if (state.getUsernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getUsernameError());
        }
    }

    public SignUpController getController() {
        return signupController;
    }
}
