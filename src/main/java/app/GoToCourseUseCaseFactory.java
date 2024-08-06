package app;

import data_access.FileEventDataAccessObject;
import data_access.FileUserDataAccessObject;
import entity.Course;
import interface_adapter.CreateEvent.CreateEventViewModel;
import interface_adapter.CreateEvent.createEventPresenter;
import interface_adapter.GoToCourse.CoursePresenter;
import interface_adapter.GoToCourse.CourseViewController;
import interface_adapter.GoToCourse.CourseViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.logOut.LogOutInputBoundary;
import interface_adapter.logOut.LogOutInteractor;
import interface_adapter.logged_In.LoggedInViewModel;
import interface_adapter.logged_In.LoggedIncontroller;
import use_case.CreateCourse.CreateCourseInputBoundary;
import use_case.CreateEvent.CreateEventInputBoundary;
import use_case.CreateEvent.CreateEventInteractor;
import use_case.CreateEvent.CreateEventOutputBoundary;
import use_case.GoToCourse.GoToCourseInputBoundary;
import use_case.GoToCourse.GoToCourseInteractor;
import use_case.MakeEvent.makeEventInputBoundary;


import use_case.joinEvent.joinEventInputBoundary;
import use_case.joinEvent.joinEventInteractor;
import use_case.joinEvent.joinEventOutputBoundary;
import view.CourseView;

import java.io.IOException;

public class GoToCourseUseCaseFactory {
    private GoToCourseUseCaseFactory(){}

    public static CourseView create(
            ViewManagerModel viewManagerModel,
            CourseViewModel courseViewModel,
            LoggedInViewModel loggedInViewModel,
            CreateEventViewModel createEventViewModel,
            FileUserDataAccessObject userDataAccessObject,
            FileEventDataAccessObject fileEventDataAccessObject
    ) {
        CourseViewController courseViewController = goToCourseUseCase(viewManagerModel, courseViewModel, loggedInViewModel, createEventViewModel, userDataAccessObject, fileEventDataAccessObject);
        return new CourseView(courseViewModel, courseViewController);
    }

    private static CourseViewController goToCourseUseCase(ViewManagerModel viewManagerModel,
                                                          CourseViewModel courseViewModel,
                                                          LoggedInViewModel loggedInViewModel,
                                                          CreateEventViewModel createEventViewModel,
                                                          FileUserDataAccessObject userDataAccessObject,
                                                          FileEventDataAccessObject fileEventDataAccessObject) {
        CoursePresenter coursePresenter = new CoursePresenter(viewManagerModel, courseViewModel, createEventViewModel, loggedInViewModel);
        GoToCourseInputBoundary goToCourseInputBoundary = new GoToCourseInteractor(coursePresenter, userDataAccessObject);
        CreateEventOutputBoundary createEventOutputBoundary = new CoursePresenter(viewManagerModel, courseViewModel, createEventViewModel, loggedInViewModel);
        CreateEventInputBoundary createEventInteractor = new CreateEventInteractor(createEventOutputBoundary,userDataAccessObject);
        joinEventOutputBoundary joinEventOutputBoundary = new CoursePresenter(viewManagerModel, courseViewModel, createEventViewModel, loggedInViewModel);
        joinEventInputBoundary joinEventUseCaseInteractor = new joinEventInteractor(fileEventDataAccessObject, joinEventOutputBoundary);
        return new CourseViewController(goToCourseInputBoundary,joinEventUseCaseInteractor,createEventInteractor);
    }
}
