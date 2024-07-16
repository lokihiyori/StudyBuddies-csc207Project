package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main_UI extends JFrame implements ActionListener {

    private final JButton searchCourseButton;
    private final JButton registeredCoursesButton;

    /**
     * A window with two buttons.
     */
    public Main_UI() {
        setTitle("Welcome to the App");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

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

    /**
     * React to a button click that results in evt.
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == searchCourseButton) {
            System.out.println("Navigating to Course Search");
            // Here you would implement the actual navigation logic
            CourseSearchView courseSearchView = new CourseSearchView();
            JFrame courseSearchFrame = new JFrame("Course Search");
            courseSearchFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            courseSearchFrame.setSize(400, 300);
            courseSearchFrame.setLocationRelativeTo(null);
            courseSearchFrame.add(courseSearchView);
            courseSearchFrame.setVisible(true);
        } else if (evt.getSource() == registeredCoursesButton) {
            System.out.println("Navigating to Registered Courses");
            // Here you would implement the actual navigation logic
            RegisteredCoursesView registeredCoursesView = new RegisteredCoursesView();
            JFrame registeredCoursesFrame = new JFrame("Registered Courses");
            registeredCoursesFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            registeredCoursesFrame.setSize(400, 300);
            registeredCoursesFrame.setLocationRelativeTo(null);
            registeredCoursesFrame.add(registeredCoursesView);
            registeredCoursesFrame.setVisible(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Main_UI welcomeUI = new Main_UI();
                welcomeUI.setVisible(true);
            }
        });
    }
}
