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

}
