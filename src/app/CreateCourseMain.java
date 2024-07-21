package app;

import data_access.CourseDataAccessObject;
import entity.GroupChatFactory;
import interface_adapter.CreateCourse.CreateCourseController;
import interface_adapter.CreateCourse.CreateCoursePresenter;
import interface_adapter.CreateCourse.CreateCourseState;
import interface_adapter.CreateCourse.CreateCourseViewModel;
import use_case.CreateCourse.CreateCourseInteractor;

public class CreateCourseMain {
    public static void main(String[] args) {
        // Setup
        CreateCourseViewModel viewModel = new CreateCourseViewModel();
        CreateCoursePresenter presenter = new CreateCoursePresenter(viewModel);
        CourseDataAccessObject dataAccessObject = new CourseDataAccessObject();
        GroupChatFactory groupChatFactory = new GroupChatFactory();
        CreateCourseInteractor interactor = new CreateCourseInteractor(presenter, dataAccessObject, groupChatFactory);
        CreateCourseController controller = new CreateCourseController(interactor);

        // Execute Use Case
        controller.executeCreateCourse("SOFTWARE DESIGN", "CSC207");

        // Check Output
        CreateCourseState state = viewModel.getState();
        System.out.println("Course Code: " + state.getCode());
        System.out.println("Course Name: " + state.getName());
    }
}
