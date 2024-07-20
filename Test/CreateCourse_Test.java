import entity.Course;
import entity.GroupChat;
import entity.GroupChatFactory;
import interface_adapter.CreateCourse.CreateCoursePresenter;
import interface_adapter.CreateCourse.CreateCourseState;
import interface_adapter.CreateCourse.CreateCourseViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.CreateCourse.CreateCourseDataAccessInterface;
import use_case.CreateCourse.CreateCourseInputData;
import use_case.CreateCourse.CreateCourseInteractor;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CreateCourse_Test {
    private CreateCourseInteractor interactor;
    private CreateCourseViewModel viewModel;
    private CreateCoursePresenter presenter;
    private InMemoryCourseDataAccessObject dataAccessObject;
    private GroupChatFactory groupChatFactory;

    @BeforeEach
    void setUp() {
        viewModel = new CreateCourseViewModel();
        presenter = new CreateCoursePresenter(viewModel);
        dataAccessObject = new InMemoryCourseDataAccessObject();
        groupChatFactory = new GroupChatFactory();
        interactor = new CreateCourseInteractor(presenter, dataAccessObject, groupChatFactory);
    }

    @Test
    void testCreateCourseSuccess() {
        CreateCourseInputData inputData = new CreateCourseInputData("CSC207", "Software Design", new GroupChat("CSC207"));
        interactor.execute(inputData);

        CreateCourseState state = viewModel.getState();
        assertEquals("CSC207", state.getCode());
        assertEquals("Software Design", state.getName());
    }

    @Test
    void testCreateCourseAlreadyExists() {
        // First create a course
        dataAccessObject.saveCourse(new Course("Course Name", "CSC207", new GroupChat("CSC207")));

        // Try to create the same course again
        CreateCourseInputData inputData = new CreateCourseInputData("CSC207", "Software Design", new GroupChat("CSC207"));
        interactor.execute(inputData);

        // Check if the error was handled
        assertTrue(viewModel.getState().getCode() == null);
    }

    private static class InMemoryCourseDataAccessObject implements CreateCourseDataAccessInterface {
        private final Map<String, Course> courses = new HashMap<>();

        @Override
        public void saveCourse(Course course) {
            courses.put(course.getCode(), course);
        }

        @Override
        public boolean existsByCode(String code) {
            return courses.containsKey(code);
        }
    }
}
