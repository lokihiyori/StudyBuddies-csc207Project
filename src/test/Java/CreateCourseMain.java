import data_access.CourseDataAccessObject;
import entity.Course;
import entity.CourseFactory;
import entity.GroupChatFactory;
import interface_adapter.CreateCourse.CreateCourseController;
import interface_adapter.CreateCourse.CreateCoursePresenter;
import interface_adapter.CreateCourse.CreateCourseState;
import interface_adapter.CreateCourse.CreateCourseViewModel;
import use_case.CreateCourse.CreateCourseInteractor;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CreateCourseMain {
    public static void main(String[] args) {
        // Setup
        String csvPath = "courses.csv"; // Path to your CSV file
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
        // Execute Use Case
        // controller.executeCreateCourse("Calculus II", "MAT237");

        List<String[]> coursesToAdd = Arrays.asList(
                new String[]{"Calculus II", "MAT237"},
                new String[]{"Computer Science I", "CSC108"},
                new String[]{"Data Structures", "CSC263"},
                new String[]{"Operating Systems", "CSC369"}
        );

        for (String[] courseData : coursesToAdd) {
            String courseName = courseData[0];
            String courseCode = courseData[1];
            controller.executeCreateCourse(courseName, courseCode);

            // Check Output
            CreateCourseState state = viewModel.getState();
            System.out.println("Course Code: " + state.getCode());
            System.out.println("Course Name: " + state.getName());

            // Verify course added to DAO
            Course createdCourse = dataAccessObject.getByCode("MAT237");
            if (createdCourse != null) {
                System.out.println("Course successfully added to DAO:");
                System.out.println("Course Code: " + createdCourse.getCode());
                System.out.println("Course Name: " + createdCourse.getName());
                System.out.println("GroupChat Code: " + createdCourse.getGroupchat().getCode());
            } else {
                System.out.println("Course was not added to DAO.");
            }
        }
    }
}
