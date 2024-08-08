package view;

import entity.Course;
import interface_adapter.UserProfile.UserProfileController;
import interface_adapter.UserProfile.UserProfilePresenter;
import interface_adapter.UserProfile.UserProfileState;
import interface_adapter.UserProfile.UserProfileViewModel;
import use_case.SearchCourse.CourseRepository;
import use_case.UserProfile.UserProfileInteractor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


public class UserprofileView extends JPanel implements PropertyChangeListener {
    private final UserProfileController userProfileController;
    private final UserProfileState userProfileState;

    private final JTextField nameField = new JTextField(20);
    private final JTextField emailField = new JTextField(20);
    private final JTextField courseField = new JTextField(20);

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

    public UserProfileController getController() {
        return userProfileController;
    }

    public UserProfileState getState() {
        return userProfileState;
    }
}