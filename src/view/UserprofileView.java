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

    private final JTextField nameField = new JTextField(15);
    private final JTextField emailField = new JTextField(15);
    private final JTextField courseField = new JTextField(15);

    public UserprofileView(UserProfileController userProfileController, UserProfileState userProfileState) {
        this.userProfileController = userProfileController;
        this.userProfileState = userProfileState;
        userProfileState.addPropertyChangeListener(this);

        JLabel titleLabel = new JLabel("User Profile");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Email:"));
        inputPanel.add(emailField);
        inputPanel.add(new JLabel("Courses (comma-separated):"));
        inputPanel.add(courseField);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(titleLabel);
        this.add(inputPanel);

        // Set fields to be non-editable since we assume the profile is pre-existing
        nameField.setEditable(false);
        emailField.setEditable(false);
        courseField.setEditable(false);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("name".equals(evt.getPropertyName())) {
            nameField.setText((String) evt.getNewValue());
        } else if ("email".equals(evt.getPropertyName())) {
            emailField.setText((String) evt.getNewValue());
        } else if ("courseCodes".equals(evt.getPropertyName())) {
            // Ensure the type cast is to java.util.List
            List<String> courseCodes = (List<String>) evt.getNewValue();
            courseField.setText(String.join(", ", courseCodes));
        }
    }
}