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

//public class LoggedInView extends JFrame implements ActionListener, PropertyChangeListener {
//    public final String viewName = "logged in";
//
//    private final JButton searchCourseButton;
//    private final JButton registeredCoursesButton;
//    private final GroupChatViewModel viewModel;
//    private final LoggedInViewModel loggedInViewModel;
//
//    public LoggedInView(GroupChatViewModel viewModel, LoggedInViewModel loggedInViewModel) {
//        this.viewModel = viewModel;
//        this.loggedInViewModel = loggedInViewModel;
//        this.loggedInViewModel.addPropertyChangeListener(this);
//        setTitle("Welcome to the App");
//        setSize(400, 200);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLocationRelativeTo(null);
//
//
//        searchCourseButton = new JButton("Go to Search Courses");
//        registeredCoursesButton = new JButton("Go to Your Courses");
//
//        // Set layout manager
//        setLayout(new GridBagLayout());
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.insets = new Insets(10, 10, 10, 10);
//
//        // Add search course button
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        add(searchCourseButton, gbc);
//
//        // Add registered courses button
//        gbc.gridx = 1;
//        gbc.gridy = 0;
//        add(registeredCoursesButton, gbc);
//
//        searchCourseButton.addActionListener(this);
//        registeredCoursesButton.addActionListener(this);
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent evt) {
//        if (evt.getSource() == searchCourseButton) {
//            System.out.println("Navigating to Course Search");
//            JFrame courseSearchFrame = new JFrame("Course Search");
//            courseSearchFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            courseSearchFrame.setSize(400, 300);
//            courseSearchFrame.setLocationRelativeTo(null);
//            courseSearchFrame.add(new CourseSearchView(viewModel));
//            courseSearchFrame.setVisible(true);
//        } else if (evt.getSource() == registeredCoursesButton) {
//            System.out.println("Navigating to Registered Courses");
//            RegisteredCoursesView registeredCoursesView = new RegisteredCoursesView();
//            JFrame registeredCoursesFrame = new JFrame("Registered Courses");
//            registeredCoursesFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            registeredCoursesFrame.setSize(400, 300);
//            registeredCoursesFrame.setLocationRelativeTo(null);
//            registeredCoursesFrame.add(registeredCoursesView);
//            registeredCoursesFrame.setVisible(true);
//        }
//    }
//
//    @Override
//    public void propertyChange(PropertyChangeEvent evt) {
//        LoggedInState state = (LoggedInState) evt.getNewValue();
//
//    }
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                GroupChatViewModel viewModel = new GroupChatViewModel(); // Assuming a default constructor
//                LoggedInViewModel loggedInViewModel = new LoggedInViewModel(); // Assuming a default constructor
//                LoggedInView welcomeUI = new LoggedInView(viewModel, loggedInViewModel);
//                welcomeUI.setVisible(true);
//            }
//        });
//    }
//}
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
        if (evt.getSource() == searchCourseButton) {
            System.out.println("Navigating to Course Search");
            // Adjust this to manage within current GUI framework
            JPanel courseSearchPanel = new CourseSearchView(viewModel); // Assuming CourseSearchView is a JPanel
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