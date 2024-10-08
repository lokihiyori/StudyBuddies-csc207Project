package view;

import SocketIO.GroupChatServer;
import data_access.CourseManager;
import interface_adapter.GroupChatViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import interface_adapter.SearchCourse.SearchCourseController;
import interface_adapter.SearchCourse.SearchCourseViewModel;

import interface_adapter.GroupChat.GroupChatFacade;
import interface_adapter.GroupChat.GroupChatFacadeImpl;


import SocketIO.GroupChatPort;

import java.util.ArrayList;
import java.util.List;

/**
 * CourseSearchView is a JPanel that provides a user interface for searching courses
 * by their code or name and allows the user to join or create group chats for the courses.
 */
public class CourseSearchView extends JPanel implements ActionListener {

    private final JTextField courseSearchField;
    private final JButton searchButton;
    private final JLabel resultLabel;
    private final GroupChatViewModel groupChatViewModel;
    private final SearchCourseViewModel searchCourseViewModel;
    private final SearchCourseController searchCourseController;

    /**
     * Constructs a new CourseSearchView with the specified view models and controller.
     *
     * @param groupChatViewModel     the GroupChatViewModel instance
     * @param searchCourseViewModel  the SearchCourseViewModel instance
     * @param searchCourseController the SearchCourseController instance
     */
    public CourseSearchView(GroupChatViewModel groupChatViewModel,
                            SearchCourseViewModel searchCourseViewModel,
                            SearchCourseController searchCourseController) {


        this.groupChatViewModel = groupChatViewModel;
        this.searchCourseViewModel = searchCourseViewModel;
        this.searchCourseController = searchCourseController;
        //this.courseViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Course Search by Code or Name");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        courseSearchField = new JTextField();
        //courseSearchField.setSize(1000, 500);
        courseSearchField.setPreferredSize(new Dimension(400, 30));
        searchButton = new JButton("Search");
        resultLabel = new JLabel("");

        // Ensure text fields have visible font color
        courseSearchField.setForeground(Color.BLACK);
        courseSearchField.setBackground(Color.WHITE);

        JPanel buttons = new JPanel();
        buttons.add(searchButton);

        searchButton.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(new LabelTextPanel(new JLabel("Course: "), courseSearchField));
        this.add(buttons);
        this.add(resultLabel);
    }

    /**
     * Handles the action event when the search button is clicked.
     *
     * @param evt the ActionEvent object
     */
    @Override
    public void actionPerformed(ActionEvent evt){

        if (evt.getSource() == searchButton) {
            //user input is stored in variable course
            String course = courseSearchField.getText();
            resultLabel.setText("");
            //
            searchCourseController.handleInput(course);


            if (course.isEmpty()) {
                resultLabel.setText("Course cannot be empty.");
            } else {
                boolean courseFound = false;
                String courseCode = null;


                //String courseNameToCheck = "Introduction to Java";
                boolean existsByName = CourseManager.courseExistsByName(course);
                //System.out.println("Course with name '" + courseNameToCheck + "' exists: " + existsByName);

                // Check if a course exists by code
                //String courseCodeToCheck = "CS101";
                boolean existsByCode = CourseManager.courseExistsByCode(course);

                if (existsByName) {
                    courseFound = true;
                    courseCode = CourseManager.getCourseCodeByName(course);
                }
                else if (existsByCode) {
                    courseFound = true;
                    courseCode = course;
                }


                handleCourseSearchResult(course, courseFound, courseCode);
            }
        }
    }

    /**
     * Handles the result of the course search and prompts the user to join or create a group chat.
     *
     * @param course      the course name or code
     * @param courseFound whether the course was found
     * @param courseCode  the course code if found
     */
    private void handleCourseSearchResult(String course, boolean courseFound, String courseCode) {
        if (courseFound) {
            int joinGroupChat = JOptionPane.showConfirmDialog(this,
                    "Group chat for " + course + " exists. Do you want to join?",
                    "Join Group Chat",
                    JOptionPane.YES_NO_OPTION);
            if (joinGroupChat == JOptionPane.YES_OPTION) {

                courseCode = courseCode.toUpperCase();
                int port = GroupChatPort.getPortByCourseCode(courseCode);
                String host = GroupChatPort.getHostFromFirstLine();

                // Step 1: Start the server
                GroupChatServer server = new GroupChatServer();
                server.startServer(port, host);

                // Step 2: Use the facade to start the client and join the group chat
                GroupChatFacade groupChatFacade = new GroupChatFacadeImpl();
                groupChatFacade.startGroupChat(courseCode, host, port);

                groupChatViewModel.joinGroupChat(course);
                resultLabel.setText("You have joined the group chat for " + course);
            } else {
                resultLabel.setText("You chose not to join the group chat.");
            }
        }
        else {
            int createGroupChat = JOptionPane.showConfirmDialog(this,
                    "No group chat for " + course + " exists. Do you want to create one?",
                    "Create Group Chat",
                    JOptionPane.YES_NO_OPTION);

            if (createGroupChat == JOptionPane.YES_OPTION) {
                //need to create a new course and add to the courses.csv
                String newCourseName = JOptionPane.showInputDialog("Enter the new course name:").toUpperCase();
                String newCourseCode = JOptionPane.showInputDialog("Enter the new course code:").toUpperCase();

                List<CourseManager> courses = new ArrayList<>();
                courses.add(new CourseManager(newCourseName, newCourseCode, newCourseCode));

                CourseManager.appendCoursesToCSV(courses);

                //assoicate this new groupchat with a unique port number and save to file
                int newPort = GroupChatPort.getLargestPortNumber() + 1;
                String host = GroupChatPort.getHostFromFirstLine();
                GroupChatPort.saveGroupChatDetails(newCourseCode, newPort, host);

                resultLabel.setText("Group chat for " + newCourseName + newCourseCode + " has been created.");
            } else {
                resultLabel.setText("You chose not to create a group chat.");
            }
        }

    }

}