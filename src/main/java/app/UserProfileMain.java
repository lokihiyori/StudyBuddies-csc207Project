package app;

import data_access.CourseDataAccessObject;
import data_access.FileUserDataAccessObject;
import entity.*;
import interface_adapter.UserProfile.UserProfileController;
import interface_adapter.UserProfile.UserProfilePresenter;
import interface_adapter.UserProfile.UserProfileState;
import interface_adapter.UserProfile.UserProfileViewModel;
import use_case.UserProfile.UserProfileInteractor;
import use_case.UserProfile.UserProfileOutputBoundary;
import view.UserprofileView;

import javax.swing.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserProfileMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            //Setup
            String csvPath = "courses.csv"; // Path to the courses CSV file
            String userCsvPath = "user.csv"; // Path to the user CSV file
            CourseFactory courseFactory = new CourseFactory();
            GroupChatFactory groupChatFactory = new GroupChatFactory();
            CommonUserFactory userFactory = new CommonUserFactory();

            // Initialize CourseDataAccessObject with required parameters
            CourseDataAccessObject dataAccessObject;
            try {
                dataAccessObject = new CourseDataAccessObject(csvPath, courseFactory, groupChatFactory);
            } catch (IOException e) {
                System.err.println("Failed to initialize CourseDataAccessObject: " + e.getMessage());
                return;
            }

            // Initialize FileUserDataAccessObject
            FileUserDataAccessObject userDataAccessObject;
            try {
                userDataAccessObject = new FileUserDataAccessObject(userCsvPath, userFactory);
            } catch (IOException e) {
                System.err.println("Failed to initialize FileUserDataAccessObject: " + e.getMessage());
                return;
            }

            // Retrieve user details
            User user = userDataAccessObject.get("John"); // Replace "John" with the desired username
            if (user == null) {
                System.err.println("User not found.");
                return;
            }

            // Initialize User Profile Components
            UserProfileViewModel viewModel = new UserProfileViewModel();
            UserProfileOutputBoundary presenter = new UserProfilePresenter(viewModel);
            UserProfileInteractor interactor = new UserProfileInteractor(presenter, dataAccessObject);
            UserProfileController controller = new UserProfileController(interactor);

            // Retrieve all courses from dataAccessObject
            Map<String, Course> courseMap = dataAccessObject.getCourseMap();
            List<String> courseCodes = courseMap.values().stream()
                    .map(Course::getCode)
                    .collect(Collectors.toList());

            // Initialize UserProfileState and UserProfileViewModel
            UserProfileState state = new UserProfileState();
//          state.setName("John");
//          state.setEmail("john@gmail.com");
            state.setName(user.getName());
            state.setEmail(user.getEmail());
            state.setCreationTime(LocalDateTime.now().toString());
            state.setCourseCodes(courseCodes);
            //state.setCourseCodes(Arrays.asList("MAT237", "CSC108", "CSC236", "CSC369"));

            // Create and initialize UserProfileView
            UserprofileView view = new UserprofileView(controller, state);

            // Setup JFrame
            JFrame frame = new JFrame("User Profile");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 300);
            frame.setContentPane(view);
            frame.setVisible(true);
        });
    }
}