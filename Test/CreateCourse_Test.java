/*
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CreateCourse_Test {
    private CreateCourseInteractor interactor;
    private CreateCourseViewModel viewModel;
    private CreateCoursePresenter presenter;
    private CourseDataAccessObject dataAccessObject;
    private GroupChatFactory groupChatFactory;
    String csvPath = "courses.csv"; // Path to your CSV file
    CourseFactory courseFactory = new CourseFactory();
    GroupChatFactory groupChatFactory = new GroupChatFactory();

    @BeforeEach
    void setUp() {
        viewModel = new CreateCourseViewModel();
        presenter = new CreateCoursePresenter(viewModel);
        try {
            dataAccessObject = new CourseDataAccessObject(csvPath, courseFactory, groupChatFactory);
        } catch (IOException e) {
            System.err.println("Failed to initialize CourseDataAccessObject: " + e.getMessage());
            return;
        }
        groupChatFactory = new GroupChatFactory();
        interactor = new CreateCourseInteractor(presenter, dataAccessObject, groupChatFactory);
    }

    @Test
    void testCreateCourseSuccess() {
        CreateCourseInputData inputData = new CreateCourseInputData("CSC207", "software design", new GroupChat("CSC207"));
        interactor.execute(inputData);
        System.out.println("Course name: " + inputData.getName());
        CreateCourseState state = viewModel.getState();
        System.out.println(viewModel.getState().getName());
        assertEquals("CSC207", state.getCode());
        assertEquals("SOFTWARE DESIGN", state.getName());
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
}
