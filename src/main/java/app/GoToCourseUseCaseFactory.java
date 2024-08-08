package app;

import data_access.FileEventDataAccessObject;
import data_access.FileUserDataAccessObject;
import interface_adapter.CreateEvent.CreateEventViewModel;
import interface_adapter.GoToCourse.CoursePresenter;
import interface_adapter.GoToCourse.CourseViewController;
import interface_adapter.GoToCourse.CourseViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_In.LoggedInViewModel;

import use_case.CreateEvent.CreateEventInputBoundary;
import use_case.CreateEvent.CreateEventInteractor;
import use_case.CreateEvent.CreateEventOutputBoundary;
import use_case.GoToCourse.GoToCourseInputBoundary;
import use_case.GoToCourse.GoToCourseInteractor;


import use_case.joinEvent.joinEventInputBoundary;
import use_case.joinEvent.joinEventInteractor;
import use_case.joinEvent.joinEventOutputBoundary;
import view.CourseView;


/**
 * Factory class for creating instances of CourseView and associated controllers and use cases.
 */
public class GoToCourseUseCaseFactory {

    /**
     * Private constructor to prevent instantiation.
     */
    private GoToCourseUseCaseFactory(){}

    /**
     * Creates a new CourseView along with its controller and initializes the required use cases.
     *
     * @param viewManagerModel          the ViewManagerModel instance
     * @param courseViewModel           the CourseViewModel instance
     * @param loggedInViewModel         the LoggedInViewModel instance
     * @param createEventViewModel      the CreateEventViewModel instance
     * @param userDataAccessObject      the FileUserDataAccessObject instance
     * @param fileEventDataAccessObject the FileEventDataAccessObject instance
     * @return a new CourseView instance
     */
    public static CourseView create(
            ViewManagerModel viewManagerModel,
            CourseViewModel courseViewModel,
            LoggedInViewModel loggedInViewModel,
            CreateEventViewModel createEventViewModel,
            FileUserDataAccessObject userDataAccessObject,
            FileEventDataAccessObject fileEventDataAccessObject
    ) {
        CourseViewController courseViewController = goToCourseUseCase(
                viewManagerModel,
                courseViewModel,
                loggedInViewModel,
                createEventViewModel,
                userDataAccessObject,
                fileEventDataAccessObject);
        return new CourseView(courseViewModel, courseViewController, createEventViewModel);
    }

    /**
     * Creates and initializes the required use cases and controller for going to a course.
     *
     * @param viewManagerModel          the ViewManagerModel instance
     * @param courseViewModel           the CourseViewModel instance
     * @param loggedInViewModel         the LoggedInViewModel instance
     * @param createEventViewModel      the CreateEventViewModel instance
     * @param userDataAccessObject      the FileUserDataAccessObject instance
     * @param fileEventDataAccessObject the FileEventDataAccessObject instance
     * @return a new CourseViewController instance
     */
    private static CourseViewController goToCourseUseCase(ViewManagerModel viewManagerModel,
                                                          CourseViewModel courseViewModel,
                                                          LoggedInViewModel loggedInViewModel,
                                                          CreateEventViewModel createEventViewModel,
                                                          FileUserDataAccessObject userDataAccessObject,
                                                          FileEventDataAccessObject fileEventDataAccessObject) {
        CoursePresenter coursePresenter = new CoursePresenter(viewManagerModel, courseViewModel,
                createEventViewModel, loggedInViewModel);
        GoToCourseInputBoundary goToCourseInputBoundary = new GoToCourseInteractor(coursePresenter, userDataAccessObject);
        CreateEventOutputBoundary createEventOutputBoundary = new CoursePresenter(viewManagerModel,
                courseViewModel, createEventViewModel, loggedInViewModel);
        CreateEventInputBoundary createEventInteractor = new CreateEventInteractor(createEventOutputBoundary,userDataAccessObject);
        joinEventOutputBoundary joinEventOutputBoundary = new CoursePresenter(viewManagerModel,
                courseViewModel, createEventViewModel, loggedInViewModel);
        joinEventInputBoundary joinEventUseCaseInteractor = new joinEventInteractor(fileEventDataAccessObject, joinEventOutputBoundary);
        return new CourseViewController(goToCourseInputBoundary,joinEventUseCaseInteractor,createEventInteractor);
    }
}
