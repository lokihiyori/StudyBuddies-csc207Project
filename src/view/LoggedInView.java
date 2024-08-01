package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import data_access.CourseDataAccessObject;
import entity.CommonUser;
import entity.GroupChat;
import interface_adapter.GroupChatViewModel;
import interface_adapter.SearchCourse.SearchCourseController;
import interface_adapter.SearchCourse.SearchCoursePresenter;
import interface_adapter.SearchCourse.SearchCourseViewModel;
import interface_adapter.logged_In.LoggedInState;
import interface_adapter.logged_In.LoggedInViewModel;
import interface_adapter.RegisteredCoursesViewModel;
import use_case.SearchCourse.SearchCourseInteractor;

public class LoggedInView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "logged in";

    private final JButton searchCourseButton;
    private final JButton registeredCoursesButton;
    private final GroupChatViewModel viewModel;
    private final LoggedInViewModel loggedInViewModel;

    public LoggedInView(GroupChatViewModel viewModel, LoggedInViewModel loggedInViewModel) {
        this.viewModel = viewModel;
        this.loggedInViewModel = loggedInViewModel;
        this.loggedInViewModel.addPropertyChangeListener(this);

        searchCourseButton = new JButton("Go to Search Courses");
        registeredCoursesButton = new JButton("Go to Your Courses");
        setPreferredSize(new Dimension(400, 400));

        // Set layout manager
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);


        // Add search course button
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(searchCourseButton, gbc);

        // Add registered courses button
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(registeredCoursesButton, gbc);
        searchCourseButton.addActionListener(this);
        registeredCoursesButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == searchCourseButton) {
            System.out.println("Navigating to Course Search");
            // Adjust this to manage within current GUI framework
            JPanel courseSearchPanel = new CourseSearchView(viewModel, new SearchCourseViewModel(), new SearchCourseController(new SearchCourseInteractor(new CourseDataAccessObject(), new SearchCoursePresenter(new SearchCourseViewModel())))); // Assuming CourseSearchView is a JPanel
            JOptionPane.showMessageDialog(this, courseSearchPanel, "Course Search", JOptionPane.PLAIN_MESSAGE);
        } else if (evt.getSource() == registeredCoursesButton) {
            System.out.println("Navigating to Registered Courses");

            // Retrieve the current user from the loggedInViewModel
            CommonUser currentUser = loggedInViewModel.getCurrentUser();

            // Create the ViewModel using the current user
            RegisteredCoursesViewModel registeredCoursesViewModel = new RegisteredCoursesViewModel(currentUser);

            // Create and display the RegisteredCoursesView
            JPanel registeredCoursesPanel = new RegisteredCoursesView(registeredCoursesViewModel);
            JOptionPane.showMessageDialog(this, registeredCoursesPanel, "Registered Courses", JOptionPane.PLAIN_MESSAGE);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoggedInState state = (LoggedInState) evt.getNewValue();
        // Update the UI based on state changes
    }
}
