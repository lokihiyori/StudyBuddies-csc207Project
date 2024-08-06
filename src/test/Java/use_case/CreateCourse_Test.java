package use_case;

import data_access.CourseDataAccessObject;
import entity.Course;
import entity.CourseFactory;
import entity.GroupChat;
import entity.GroupChatFactory;
import interface_adapter.CreateCourse.CreateCoursePresenter;
import interface_adapter.CreateCourse.CreateCourseState;
import interface_adapter.CreateCourse.CreateCourseViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.CreateCourse.CreateCourseInputData;
import use_case.CreateCourse.CreateCourseInteractor;


import java.io.IOException;

import static org.junit.Assert.*;

public class CreateCourse_Test {
    private CreateCourseInteractor interactor;
    private CreateCourseViewModel viewModel;
    private CreateCoursePresenter presenter;
    private CourseDataAccessObject dataAccessObject;
    private CourseFactory courseFactory;
    private GroupChatFactory groupChatFactory;

    @BeforeEach
    void setUp() {
        viewModel = new CreateCourseViewModel();
        presenter = new CreateCoursePresenter(viewModel);
        courseFactory = new CourseFactory();
        groupChatFactory = new GroupChatFactory();
        try {
            dataAccessObject = new CourseDataAccessObject("courses.csv", courseFactory, groupChatFactory);
        } catch (IOException e) {
            System.err.println("Failed to initialize CourseDataAccessObject: " + e.getMessage());
        }
        interactor = new CreateCourseInteractor(presenter, dataAccessObject, groupChatFactory);
    }

    @Test
    void testCreateCourseAlreadyExists() {
        // First create a course
        dataAccessObject.saveCourse(new Course("FM", "ACT240", new GroupChat("ACT240")));

        // Try to create the same course again
        CreateCourseInputData inputData = new CreateCourseInputData("FM", "ACT240", new GroupChat("ACT240"));
        interactor.execute(inputData);

        // Check if the error was handled
        CreateCourseState state = viewModel.getState();
        assertNull(state.getCode());
        assertNull(state.getName());
    }
}
