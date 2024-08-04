package app;

import data_access.FileUserDataAccessObject;
import entity.Course;
import interface_adapter.GoToCourse.CoursePresenter;
import interface_adapter.GoToCourse.CourseViewController;
import interface_adapter.GoToCourse.CourseViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.logOut.LogOutInputBoundary;
import interface_adapter.logOut.LogOutInteractor;
import interface_adapter.logged_In.LoggedInViewModel;
import interface_adapter.logged_In.LoggedIncontroller;
import use_case.GoToCourse.GoToCourseInputBoundary;
import use_case.GoToCourse.GoToCourseInteractor;
import view.CourseView;

import java.io.IOException;

public class GoToCourseUseCaseFactory {
    private GoToCourseUseCaseFactory(){}

    public static CourseView create(
            ViewManagerModel viewManagerModel,
            CourseViewModel courseViewModel,
            LoggedInViewModel loggedInViewModel,
            FileUserDataAccessObject userDataAccessObject
    ) {
        CourseViewController courseViewController = goToCourseUseCase(viewManagerModel, courseViewModel, loggedInViewModel, userDataAccessObject);
        return new CourseView(courseViewModel, courseViewController);
    }

    private static CourseViewController goToCourseUseCase(ViewManagerModel viewManagerModel,
                                                          CourseViewModel courseViewModel,
                                                          LoggedInViewModel loggedInViewModel,
                                                          FileUserDataAccessObject userDataAccessObject) {
        CoursePresenter coursePresenter = new CoursePresenter(viewManagerModel, courseViewModel, loggedInViewModel);
        GoToCourseInputBoundary goToCourseInputBoundary = new GoToCourseInteractor(coursePresenter, userDataAccessObject);
        return new CourseViewController(goToCourseInputBoundary);
    }
}
