package app;

import data_access.CourseDataAccessObject;
import data_access.CourseListDAO;
import entity.Course;
import entity.CourseFactory;
import entity.GroupChat;
import entity.GroupChatFactory;
import interface_adapter.UserProfile.UserProfileController;
import interface_adapter.UserProfile.UserProfilePresenter;
import interface_adapter.UserProfile.UserProfileState;
import interface_adapter.UserProfile.UserProfileViewModel;
import use_case.SearchCourse.CourseRepository;
import use_case.UserProfile.UserProfileInteractor;
import use_case.UserProfile.UserProfileOutputBoundary;
import view.UserprofileView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserProfileMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Initialize the CourseRepository
//            CourseRepository courseRepository;
//            try {
//                CourseFactory courseFactory = new CourseFactory();
//                GroupChatFactory groupChatFactory = new GroupChatFactory();
//                courseRepository = new CourseDataAccessObject("path/to/courses.csv", courseFactory, groupChatFactory);
//            } catch (IOException e) {
//                e.printStackTrace();
//                return;
//            }
            CourseRepository courseRepository = new CourseRepository() {
                private final Map<String, Course> courseMap = new HashMap<>();

                {
                    // Add some default courses
                    courseMap.put("CS101", new Course("Intro to Computer Science", "CS101", null));
                    courseMap.put("MATH201", new Course("Calculus II", "MATH201", null));
                }

                /**
                 * @param course
                 */
                @Override
                public void addCourse(Course course) {

                }

                @Override
                public Course findCourseByCode(String code) {
                    return courseMap.get(code);
                }

                @Override
                public Course findCourseByName(String name) {
                    return courseMap.values().stream()
                            .filter(course -> course.getName().equalsIgnoreCase(name))
                            .findFirst()
                            .orElse(null);
                }
            };

            // Initialize User Profile Components
            UserProfileViewModel viewModel = new UserProfileViewModel();
            UserProfileOutputBoundary presenter = new UserProfilePresenter(viewModel);
            // Ensure the constructor matches the required arguments
            UserProfileInteractor interactor = new UserProfileInteractor(presenter, courseRepository);
            UserProfileController controller = new UserProfileController(interactor);

            // Initialize UserProfileState and UserProfileViewModel
            UserProfileState state = new UserProfileState();
            state.setName("John");
            state.setEmail("Doe");
            state.setCreationTime(LocalDateTime.now().toString());
            state.setCourseCodes(Arrays.asList("CSC207", "CSC236", "MAT237"));

            // Create and initialize UserProfileView
            UserprofileView view = new UserprofileView(controller, state);

            // Setup JFrame
            JFrame frame = new JFrame("User Profile");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setContentPane(view);
            frame.setVisible(true);

            // Example to create a user profile
            List<String> courseCodes = Arrays.asList("CS101", "MATH201");
            controller.createUserProfile("John Doe", "password123", "john.doe@example.com", LocalDateTime.now(), courseCodes);
        });
    }
}