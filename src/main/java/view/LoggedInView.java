/*
package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import data_access.CourseDataAccessObject;
import interface_adapter.GoToCourse.CourseViewModel;
import interface_adapter.GroupChatViewModel;
import interface_adapter.SearchCourse.SearchCourseController;
import interface_adapter.SearchCourse.SearchCoursePresenter;
import interface_adapter.SearchCourse.SearchCourseViewModel;
import interface_adapter.logged_In.LoggedInState;
import interface_adapter.logged_In.LoggedInViewModel;
import interface_adapter.logged_In.LoggedIncontroller;
import use_case.SearchCourse.SearchCourseInteractor;

public class LoggedInView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "logged in";

    private final JButton searchCourseButton;
    private final JButton registeredCoursesButton;
    private final GroupChatViewModel viewModel;
    private final LoggedInViewModel loggedInViewModel;
    private final LoggedIncontroller loggedIncontroller;
    private final CourseViewModel courseViewModel;

    public LoggedInView(GroupChatViewModel viewModel, LoggedInViewModel loggedInViewModel, LoggedIncontroller loggedIncontroller, CourseViewModel courseViewModel) {
        this.viewModel = viewModel;
        this.loggedInViewModel = loggedInViewModel;
        this.loggedInViewModel.addPropertyChangeListener(this);
        this.loggedIncontroller = loggedIncontroller;
        this.courseViewModel = courseViewModel;
        this.courseViewModel.addPropertyChangeListener(this);

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

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == searchCourseButton) {
            System.out.println("Navigating to Course Search");
            // Adjust this to manage within current GUI framework
            //CourseViewModel courseViewModel = new CourseViewModel();
            JPanel courseSearchPanel = new CourseSearchView(viewModel, new SearchCourseViewModel(), new SearchCourseController(new SearchCourseInteractor(new CourseDataAccessObject(), new SearchCoursePresenter(new SearchCourseViewModel()))), courseViewModel); // Assuming CourseSearchView is a JPanel
            JOptionPane.showMessageDialog(this, courseSearchPanel, "Course Search", JOptionPane.PLAIN_MESSAGE);
//         }else if (evt.getSource() == registeredCoursesButton) {
//            System.out.println("Navigating to Registered Courses");
//
//            // Retrieve the current user from the loggedInViewModel
//            LoggedInState currentState = loggedInViewModel.getState();
//
//            loggedIncontroller.executeGoToCourse(
//                    currentState.getUsername()
            ;
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoggedInState state = (LoggedInState) evt.getNewValue();
        // Update the UI based on state changes
    }
}

 */

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

public class LoggedInView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "logged in";

    private final JButton searchCourseButton;
    private final JButton registeredCoursesButton;
    private final GroupChatViewModel viewModel;
    private final LoggedInViewModel loggedInViewModel;
    private final LoggedIncontroller loggedIncontroller;

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

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == searchCourseButton) {
            System.out.println("Navigating to Course Search");
            // Adjust this to manage within current GUI framework
            JPanel courseSearchPanel = new CourseSearchView(viewModel, new SearchCourseViewModel(), new SearchCourseController(new SearchCourseInteractor(new CourseDataAccessObject(), new SearchCoursePresenter(new SearchCourseViewModel())))); // Assuming CourseSearchView is a JPanel
            JOptionPane.showMessageDialog(this, courseSearchPanel, "Course Search", JOptionPane.PLAIN_MESSAGE);
//         }else if (evt.getSource() == registeredCoursesButton) {
//            System.out.println("Navigating to Registered Courses");
//
//            // Retrieve the current user from the loggedInViewModel
//            LoggedInState currentState = loggedInViewModel.getState();
//
//            loggedIncontroller.executeGoToCourse(
//                    currentState.getUsername()
            ;
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoggedInState state = (LoggedInState) evt.getNewValue();
        // Update the UI based on state changes
    }

    public LoggedIncontroller getController() {
        return loggedIncontroller;
    }
}
