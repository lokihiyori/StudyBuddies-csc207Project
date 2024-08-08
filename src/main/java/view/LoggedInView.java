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
import interface_adapter.logged_In.LoggedIncontroller;
import interface_adapter.login.LoginState;
import use_case.SearchCourse.SearchCourseInteractor;

/**
 * LoggedInView is a JPanel that provides a user interface for users who have logged in.
 * It allows users to search for courses or view their registered courses.
 */
public class LoggedInView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "logged in";

    private final JButton searchCourseButton;
    private final JButton registeredCoursesButton;
    private final GroupChatViewModel viewModel;
    private final LoggedInViewModel loggedInViewModel;
    private final LoggedIncontroller loggedIncontroller;

    /**
     * Constructs a new LoggedInView with the specified view models and controller.
     *
     * @param viewModel          the GroupChatViewModel instance
     * @param loggedInViewModel  the LoggedInViewModel instance
     * @param loggedIncontroller the LoggedIncontroller instance
     */
    public LoggedInView(GroupChatViewModel viewModel, LoggedInViewModel loggedInViewModel, LoggedIncontroller loggedIncontroller) {
        this.viewModel = viewModel;
        this.loggedInViewModel = loggedInViewModel;
        this.loggedInViewModel.addPropertyChangeListener(this);
        this.loggedIncontroller = loggedIncontroller;

        searchCourseButton = new JButton("Go to Search Courses");
        registeredCoursesButton = new JButton("Go to Your Courses");

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
        registeredCoursesButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(registeredCoursesButton)) {
                            LoggedInState currentState = loggedInViewModel.getState();
                            loggedIncontroller.executeGoToCourse(
                                    currentState.getUsername()
                            );
                        }
                    }
                });
    }

    /**
     * Handles action events for the buttons.
     *
     * @param evt the ActionEvent object
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == searchCourseButton) {
            System.out.println("Navigating to Course Search");
            // Adjust this to manage within current GUI framework
            JPanel courseSearchPanel = new CourseSearchView(viewModel, new SearchCourseViewModel(), new SearchCourseController(new SearchCourseInteractor(new CourseDataAccessObject(), new SearchCoursePresenter(new SearchCourseViewModel())))); // Assuming CourseSearchView is a JPanel
            JOptionPane.showMessageDialog(this, courseSearchPanel, "Course Search", JOptionPane.PLAIN_MESSAGE);

        }
    }

    /**
     * Handles property changes in the view model.
     *
     * @param evt the PropertyChangeEvent object
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoggedInState state = (LoggedInState) evt.getNewValue();
        // Update the UI based on state changes
    }

    /**
     * Gets the LoggedIncontroller associated with this view.
     *
     * @return the LoggedIncontroller instance
     */
    public LoggedIncontroller getController() {
        return loggedIncontroller;
    }
}
