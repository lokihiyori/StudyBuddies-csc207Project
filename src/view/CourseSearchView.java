package view;

import interface_adapter.GroupChatViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import interface_adapter.SearchCourse.SearchCourseController;
import interface_adapter.SearchCourse.SearchCoursePresenter;
import interface_adapter.SearchCourse.SearchCourseViewModel;
import data_access.CourseListDAO;
import data_access.CourseDataAccessObject;
import use_case.SearchCourse.SearchCourseInteractor;
import use_case.SearchCourse.SearchCourseInputBoundary;
import use_case.SearchCourse.SearchCourseOutputBoundary;

public class CourseSearchView extends JPanel implements ActionListener {

    private final JTextField courseSearchField;
    private final JButton searchButton;
    private final JLabel resultLabel;
    private final GroupChatViewModel groupChatViewModel;
    private final SearchCourseViewModel searchCourseViewModel;
    private final SearchCourseController searchCourseController;


    public CourseSearchView(GroupChatViewModel groupChatViewModel,
                            SearchCourseViewModel searchCourseViewModel,
                            SearchCourseController searchCourseController) {


        this.groupChatViewModel = groupChatViewModel;
        this.searchCourseViewModel = searchCourseViewModel;
        this.searchCourseController = searchCourseController;

        JLabel title = new JLabel("Course Search by Code or Name");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        courseSearchField = new JTextField(20);
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
        this.add(new LabelTextPanel(new JLabel("Course"), courseSearchField));
        this.add(buttons);
        this.add(resultLabel);
    }

    @Override
    public void actionPerformed(ActionEvent evt){

        if (evt.getSource() == searchButton) {
            String course = courseSearchField.getText();
            resultLabel.setText("");
            //
            searchCourseController.handleInput(course);


            if (course.isEmpty()) {
                resultLabel.setText("Course cannot be empty.");
            } else {
                boolean courseFound;
                if (searchCourseViewModel.getCourse() != null){
                    courseFound = true;
                    resultLabel.setText("Course is Found.");
                }
                else{
                    courseFound = false;
                    resultLabel.setText("Course is not Found.");
                }
                //boolean courseFound = viewModel.searchForCourse(course);
                handleCourseSearchResult(course, courseFound);
            }
        }
    }

    private void handleCourseSearchResult(String course, boolean courseFound) {
        if (courseFound) {
            int joinGroupChat = JOptionPane.showConfirmDialog(this,
                    "Group chat for " + course + " exists. Do you want to join?",
                    "Join Group Chat",
                    JOptionPane.YES_NO_OPTION);
            if (joinGroupChat == JOptionPane.YES_OPTION) {
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
                groupChatViewModel.createGroupChat(course);
                resultLabel.setText("Group chat for " + course + " has been created.");
            } else {
                resultLabel.setText("You chose not to create a group chat.");
            }
        }

            /*
            boolean groupChatExists = viewModel.checkGroupChatExists(course);
            if (groupChatExists) {
                int joinGroupChat = JOptionPane.showConfirmDialog(this,
                        "Group chat for " + course + " exists. Do you want to join?",
                        "Join Group Chat",
                        JOptionPane.YES_NO_OPTION);

                if (joinGroupChat == JOptionPane.YES_OPTION) {
                    viewModel.joinGroupChat(course);
                    resultLabel.setText("You have joined the group chat for " + course);
                } else {
                    resultLabel.setText("You chose not to join the group chat.");
                }
            } else {
                int createGroupChat = JOptionPane.showConfirmDialog(this,
                        "No group chat for " + course + " exists. Do you want to create one?",
                        "Create Group Chat",
                        JOptionPane.YES_NO_OPTION);

                if (createGroupChat == JOptionPane.YES_OPTION) {
                    viewModel.createGroupChat(course);
                    resultLabel.setText("Group chat for " + course + " has been created.");
                } else {
                    resultLabel.setText("You chose not to create a group chat.");
                }
            }

        }
        */

    }

    public static void main(String[] args) {

        GroupChatViewModel groupChatViewModel = new GroupChatViewModel(); // Create an instance of GroupChatViewModel
        //CourseListDAO courseList = new CourseListDAO();
        CourseDataAccessObject courseList = new CourseDataAccessObject();
        SearchCourseViewModel searchCourseViewModel = new SearchCourseViewModel();
        SearchCourseOutputBoundary presenter = new SearchCoursePresenter(searchCourseViewModel);
        SearchCourseInputBoundary searchCourseInteractor = new SearchCourseInteractor(courseList, presenter);
        SearchCourseController searchCourseController = new SearchCourseController(searchCourseInteractor);


        JFrame frame = new JFrame("Course Search");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        CourseSearchView courseSearchView = new CourseSearchView(groupChatViewModel,
                searchCourseViewModel,
                searchCourseController);
        frame.add(courseSearchView);
        frame.setVisible(true);
    }
}