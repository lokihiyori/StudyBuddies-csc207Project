package view;

import interface_adapter.GroupChatViewModel;
import interface_adapter.logged_In.LoggedInState;
import interface_adapter.logged_In.LoggedInViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


import entity.Course;
import entity.GroupChat;
import data_access.CourseListDAO;
import data_access.CourseDataAccessObject;
import interface_adapter.SearchCourse.SearchCourseController;
import interface_adapter.SearchCourse.SearchCoursePresenter;
import interface_adapter.SearchCourse.SearchCourseViewModel;
import use_case.SearchCourse.SearchCourseInputBoundary;
import use_case.SearchCourse.SearchCourseOutputBoundary;
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

        //CourseListDAO courseList = new CourseListDAO();
        CourseDataAccessObject courseList = new CourseDataAccessObject();
        SearchCourseViewModel searchCourseViewModel = new SearchCourseViewModel();
        SearchCourseOutputBoundary presenter = new SearchCoursePresenter(searchCourseViewModel);
        SearchCourseInputBoundary searchCourseInteractor = new SearchCourseInteractor(courseList, presenter);
        SearchCourseController searchCourseController = new SearchCourseController(searchCourseInteractor);

        //initialize some courses
        GroupChat gc1 = new GroupChat("CSC207");
        GroupChat gc2 = new GroupChat("MAT141");
        GroupChat gc3 = new GroupChat("PHY131");

        // Adding some sample courses to the repository
        courseList.addCourse(new Course("SOFTWARE DESIGN", "CSC207", gc1));
        courseList.addCourse(new Course("CALCULUS I", "MAT141", gc2));
        courseList.addCourse(new Course("PHYSICS I", "PHY131", gc3));


        if (evt.getSource() == searchCourseButton) {
            System.out.println("Navigating to Course Search");
            // Adjust this to manage within current GUI framework
            JPanel courseSearchPanel = new CourseSearchView(viewModel, searchCourseViewModel, searchCourseController); // Assuming CourseSearchView is a JPanel
            JOptionPane.showMessageDialog(this, courseSearchPanel, "Course Search", JOptionPane.PLAIN_MESSAGE);
        } else if (evt.getSource() == registeredCoursesButton) {
            System.out.println("Navigating to Registered Courses");
            JPanel registeredCoursesPanel = new RegisteredCoursesView(); // Assuming RegisteredCoursesView is a JPanel
            JOptionPane.showMessageDialog(this, registeredCoursesPanel, "Registered Courses", JOptionPane.PLAIN_MESSAGE);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LoggedInState state = (LoggedInState) evt.getNewValue();
        // Update the UI based on state changes
    }
}