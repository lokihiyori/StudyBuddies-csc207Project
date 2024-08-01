package view;

import data_access.CourseDataAccessObject;
import data_access.GroupChatDataAccessObject;
import entity.GroupChatFactory;
import interface_adapter.EnrollInCourse.EnrollInCourseController;
import interface_adapter.GroupChatViewModel;
import interface_adapter.SearchCourse.SearchCourseController;
import interface_adapter.SearchCourse.SearchCourseViewModel;
import interface_adapter.ViewModel;
import interface_adapter.logged_In.LoggedInViewModel;
import use_case.EnrollInCourse.EnrollInCourseInputBoundary;
import use_case.EnrollInCourse.EnrollInCourseInteractor;
import use_case.EnrollInCourse.EnrollInCourseOutputBoundary;
import use_case.SearchCourse.SearchCourseInputBoundary;
import use_case.SearchCourse.SearchCourseInteractor;
import use_case.SearchCourse.SearchCourseOutputBoundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class CourseSearchView extends JPanel implements ActionListener {
    private final JTextField courseSearchField;
    private final JButton searchButton;
    private final JLabel resultLabel;
    private final GroupChatViewModel groupChatViewModel;
    private final SearchCourseViewModel searchCourseViewModel;
    private final SearchCourseController searchCourseController;
    private final EnrollInCourseController enrollInCourseController;
    private final LoggedInViewModel loggedInViewModel;

    public CourseSearchView(GroupChatViewModel groupChatViewModel,
                            SearchCourseViewModel searchCourseViewModel,
                            SearchCourseController searchCourseController,
                            EnrollInCourseController enrollInCourseController,
                            LoggedInViewModel loggedInViewModel) {
        this.groupChatViewModel = groupChatViewModel;
        this.searchCourseViewModel = searchCourseViewModel;
        this.searchCourseController = searchCourseController;
        this.enrollInCourseController = enrollInCourseController;
        this.loggedInViewModel = loggedInViewModel;

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

        // Adding property change listeners
        searchCourseViewModel.addPropertyChangeListener(evt -> {
            if ("course".equals(evt.getPropertyName())) {
                handleCourseSearchResult(searchCourseViewModel.getCourse().getCode(), true);
            } else if ("message".equals(evt.getPropertyName())) {
                resultLabel.setText(searchCourseViewModel.getMessage());
            }
        });

        groupChatViewModel.addPropertyChangeListener(evt -> {
            if ("groupChatCreated".equals(evt.getPropertyName())) {
                JOptionPane.showMessageDialog(this, "Group chat created for course: " + evt.getNewValue());
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == searchButton) {
            String course = courseSearchField.getText();
            resultLabel.setText("");
            searchCourseController.handleInput(course);

            if (course.isEmpty()) {
                resultLabel.setText("Course cannot be empty.");
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
                enrollInCourseController.enrollInCourse(course, loggedInViewModel.getCurrentUser().getEmail());
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
                groupChatViewModel.createGroupChat(course);
                resultLabel.setText("Group chat for " + course + " has been created.");
            } else {
                resultLabel.setText("You chose not to create a group chat.");
            }
        }
    }

    public static void main(String[] args) throws IOException {
        GroupChatViewModel groupChatViewModel = new GroupChatViewModel();
        CourseDataAccessObject courseList = new CourseDataAccessObject();
        SearchCourseViewModel searchCourseViewModel = new SearchCourseViewModel();
        SearchCourseOutputBoundary searchCoursePresenter = new interface_adapter.SearchCourse.SearchCoursePresenter(searchCourseViewModel);
        SearchCourseInputBoundary searchCourseInteractor = new SearchCourseInteractor(courseList, searchCoursePresenter);
        SearchCourseController searchCourseController = new SearchCourseController(searchCourseInteractor);
        GroupChatDataAccessObject groupChatDAO = new GroupChatDataAccessObject("path/to/csv", new GroupChatFactory());
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        SearchCourseViewModel viewModel = new SearchCourseViewModel();
        EnrollInCourseOutputBoundary enrollPresenter = new interface_adapter.EnrollInCourse.EnrollInCoursePresenter(viewModel);

        EnrollInCourseInputBoundary enrollInCourseInteractor = new EnrollInCourseInteractor(groupChatDAO, groupChatViewModel);
        EnrollInCourseController enrollInCourseController = new EnrollInCourseController(enrollInCourseInteractor);

        JFrame frame = new JFrame("Course Search");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        CourseSearchView courseSearchView = new CourseSearchView(groupChatViewModel,
                searchCourseViewModel,
                searchCourseController,
                enrollInCourseController,
                loggedInViewModel);
        frame.add(courseSearchView);
        frame.setVisible(true);
    }
}
