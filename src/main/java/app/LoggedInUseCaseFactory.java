package app;

import data_access.FileUserDataAccessObject;
import interface_adapter.CreateEvent.CreateEventViewModel;
import interface_adapter.GoToCourse.CoursePresenter;
import interface_adapter.GoToCourse.CourseViewModel;
import interface_adapter.GroupChatViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_In.LoggedInViewModel;
import interface_adapter.logged_In.LoggedIncontroller;
import use_case.GoToCourse.GoToCourseInputBoundary;
import use_case.GoToCourse.GoToCourseInteractor;
import use_case.GoToCourse.GoToCourseOutputBoundary;
import view.LoggedInView;

/**
 * Factory class for creating instances of LoggedInView and associated controllers and use cases.
 */
public class LoggedInUseCaseFactory {

    /**
     * Private constructor to prevent instantiation.
     */
    private LoggedInUseCaseFactory(){
    }

    /**
     * Creates a new LoggedInView along with its controller and initializes the required use cases.
     *
     * @param viewManagerModel     the ViewManagerModel instance
     * @param loggedInViewModel    the LoggedInViewModel instance
     * @param courseViewModel      the CourseViewModel instance
     * @param createEventViewModel the CreateEventViewModel instance
     * @param userDataAccessObject the FileUserDataAccessObject instance
     * @return a new LoggedInView instance
     */
    public static LoggedInView create(ViewManagerModel viewManagerModel, LoggedInViewModel
            loggedInViewModel, CourseViewModel courseViewModel, CreateEventViewModel createEventViewModel,
                                      FileUserDataAccessObject userDataAccessObject){
        LoggedIncontroller loggedIncontroller =
                createLoggedInUseCase(viewManagerModel, loggedInViewModel,
                        courseViewModel, createEventViewModel, userDataAccessObject);
        return new LoggedInView(new GroupChatViewModel(), loggedInViewModel,loggedIncontroller);
    }

    /**
     * Creates and initializes the required use cases and controller for the logged-in state.
     *
     * @param viewManagerModel        the ViewManagerModel instance
     * @param loggedInViewModel       the LoggedInViewModel instance
     * @param courseViewModel         the CourseViewModel instance
     * @param createEventViewModel    the CreateEventViewModel instance
     * @param fileUserDataAccessObject the FileUserDataAccessObject instance
     * @return a new LoggedIncontroller instance
     */
    private static LoggedIncontroller createLoggedInUseCase(
            ViewManagerModel viewManagerModel, LoggedInViewModel loggedInViewModel, CourseViewModel courseViewModel,
            CreateEventViewModel createEventViewModel,
            FileUserDataAccessObject fileUserDataAccessObject){
        GoToCourseOutputBoundary goToCourseOutputBoundary = new CoursePresenter(viewManagerModel, courseViewModel,
                createEventViewModel, loggedInViewModel);
        GoToCourseInputBoundary goToCourseInteractor = new GoToCourseInteractor(goToCourseOutputBoundary,
                fileUserDataAccessObject);
        return new LoggedIncontroller(goToCourseInteractor);
    }


}
