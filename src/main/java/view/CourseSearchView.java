package view;

import SocketIO.GroupChatClient;
import SocketIO.GroupChatServer;
import interface_adapter.GroupChatViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import interface_adapter.SearchCourse.SearchCourseController;
import interface_adapter.SearchCourse.SearchCoursePresenter;
import interface_adapter.SearchCourse.SearchCourseViewModel;
import data_access.CourseDataAccessObject;
import use_case.SearchCourse.SearchCourseInteractor;
import use_case.SearchCourse.SearchCourseInputBoundary;
import use_case.SearchCourse.SearchCourseOutputBoundary;

import entity.CourseFactory;
import entity.GroupChatFactory;
import interface_adapter.CreateCourse.CreateCourseController;
import interface_adapter.CreateCourse.CreateCoursePresenter;
import interface_adapter.CreateCourse.CreateCourseViewModel;
import use_case.CreateCourse.CreateCourseInteractor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

        JLabel title = new JLabel("Course Search by Code");
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

                //check if the course exists in courses.csv
                String filePath = "courses.csv";
                String line;
                String csvSplitBy = ",";

                try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                    // Skip the header
                    br.readLine();
                    while ((line = br.readLine()) != null) {
                        String[] courseCheck = line.split(csvSplitBy);
                        if (courseCheck[0].trim().equalsIgnoreCase(course.toUpperCase())) {
                            courseFound = true;
                            courseCode = courseCheck[1];
                            break;
                        } else if (courseCheck[1].trim().equalsIgnoreCase(course.toUpperCase())){
                            courseFound = true;
                            courseCode = course.toUpperCase();
                            break;
                        }

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }


                /*
                if (searchCourseViewModel.getCourse() != null){
                    courseFound = true;
                    resultLabel.setText("Course is Found.");
                }
                else{
                    courseFound = false;
                    resultLabel.setText("Course is not Found.");
                }

                 */
                //boolean courseFound = viewModel.searchForCourse(course);
                handleCourseSearchResult(course, courseFound, courseCode);
            }
        }
    }

    private void handleCourseSearchResult(String course, boolean courseFound, String courseCode) {
        if (courseFound) {
            int joinGroupChat = JOptionPane.showConfirmDialog(this,
                    "Group chat for " + course + " exists. Do you want to join?",
                    "Join Group Chat",
                    JOptionPane.YES_NO_OPTION);
            if (joinGroupChat == JOptionPane.YES_OPTION) {


                //join group chat !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                // Start the server in a separate thread
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        GroupChatServer server = new GroupChatServer();
                        server.startServer();
                    }
                }).start();

                // Give the server a moment to start up
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // Start the client
                GroupChatClient groupChatClient = new GroupChatClient();
                groupChatClient.startChat(courseCode);





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
                //String groupChat = newCourseCode;

                String csvPath = "courses.csv";
                CourseFactory courseFactory = new CourseFactory();
                GroupChatFactory groupChatFactory = new GroupChatFactory();

                // Initialize CourseDataAccessObject with required parameters
                CourseDataAccessObject dataAccessObject;
                try {
                    dataAccessObject = new CourseDataAccessObject(csvPath, courseFactory, groupChatFactory);
                } catch (IOException e) {
                    System.err.println("Failed to initialize CourseDataAccessObject: " + e.getMessage());
                    return;
                }

                CreateCourseViewModel viewModel = new CreateCourseViewModel();
                CreateCoursePresenter presenter = new CreateCoursePresenter(viewModel);
                CreateCourseInteractor interactor = new CreateCourseInteractor(presenter, dataAccessObject, groupChatFactory);
                CreateCourseController controller = new CreateCourseController(interactor);
                controller.executeCreateCourse(newCourseName, newCourseCode);

                groupChatViewModel.createGroupChat(course);
                resultLabel.setText("Group chat for " + newCourseName + newCourseCode + " has been created.");
            } else {
                resultLabel.setText("You chose not to create a group chat.");
            }
        }

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