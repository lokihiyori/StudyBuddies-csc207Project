package view;

import interface_adapter.UserProfile.UserProfileController;
import interface_adapter.UserProfile.UserProfileState;


import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

/**
 * UserprofileView is a JPanel that displays the user's profile information.
 */
public class UserprofileView extends JPanel implements PropertyChangeListener {
    private final UserProfileController userProfileController;
    private final UserProfileState userProfileState;

    private final JTextField nameField = new JTextField(20);
    private final JTextField emailField = new JTextField(20);
    private final JTextField courseField = new JTextField(20);

    /**
     * Constructs a new UserprofileView with the specified controller and state.
     *
     * @param userProfileController the UserProfileController instance
     * @param userProfileState      the UserProfileState instance
     */
    public UserprofileView(UserProfileController userProfileController, UserProfileState userProfileState) {
        this.userProfileController = userProfileController;
        this.userProfileState = userProfileState;
        userProfileState.addPropertyChangeListener(this);

        nameField.setText(userProfileState.getName());
        nameField.setText(userProfileState.getName());
        nameField.setEditable(false); // Make name field uneditable

        emailField.setText(userProfileState.getEmail());
        emailField.setEditable(false); // Make email field uneditable

        courseField.setText(String.join(", ", userProfileState.getCourseCodes()));
        courseField.setEditable(false); // Make course field uneditable

        JLabel titleLabel = new JLabel("User Profile");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(new Color(4, 91, 205));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Email:"));
        inputPanel.add(emailField);
        inputPanel.add(new JLabel("Courses"));
        inputPanel.add(courseField);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(titleLabel);
        this.add(Box.createVerticalStrut(20));
        this.add(inputPanel);
    }

    /**
     * Handles property changes in the user profile state.
     *
     * @param evt the PropertyChangeEvent object
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("name".equals(evt.getPropertyName())) {
            nameField.setText((String) evt.getNewValue());
        } else if ("email".equals(evt.getPropertyName())) {
            emailField.setText((String) evt.getNewValue());
        } else if ("creationTime".equals(evt.getPropertyName())) {
            // Handle creation time if necessary
        } else if ("courseCodes".equals(evt.getPropertyName())) {
            List<String> courseCodes = (List<String>) evt.getNewValue();
            courseField.setText(String.join(", ", courseCodes));
        }
    }

    /**
     * Gets the UserProfileController associated with this view.
     *
     * @return the UserProfileController instance
     */
    public UserProfileController getController() {
        return userProfileController;
    }

    /**
     * Gets the UserProfileState associated with this view.
     *
     * @return the UserProfileState instance
     */
    public UserProfileState getState() {
        return userProfileState;
    }
}