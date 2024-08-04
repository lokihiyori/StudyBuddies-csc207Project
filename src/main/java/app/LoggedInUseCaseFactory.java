package app;

import data_access.FileUserDataAccessObject;
import interface_adapter.GoToCourse.CoursePresenter;
import interface_adapter.GoToCourse.CourseViewModel;
import interface_adapter.GroupChatViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_In.LoggedInPresenter;
import interface_adapter.logged_In.LoggedInViewModel;
import interface_adapter.logged_In.LoggedIncontroller;
import use_case.GoToCourse.GoToCourseInputBoundary;
import use_case.GoToCourse.GoToCourseInteractor;
import use_case.GoToCourse.GoToCourseOutputBoundary;
import view.LoggedInView;

public class LoggedInUseCaseFactory {
    private LoggedInUseCaseFactory(){
    }

    public static LoggedInView create(ViewManagerModel viewManagerModel, LoggedInViewModel loggedInViewModel,
                                      CourseViewModel courseViewModel, FileUserDataAccessObject userDataAccessObject){
        LoggedIncontroller loggedIncontroller =
                createLoggedInUseCase(viewManagerModel, loggedInViewModel, courseViewModel, userDataAccessObject);
        return new LoggedInView(new GroupChatViewModel(), loggedInViewModel,loggedIncontroller);
    }

    private static LoggedIncontroller createLoggedInUseCase(
            ViewManagerModel viewManagerModel, LoggedInViewModel loggedInViewModel, CourseViewModel courseViewModel,
            FileUserDataAccessObject fileUserDataAccessObject){
        GoToCourseOutputBoundary goToCourseOutputBoundary = new CoursePresenter(viewManagerModel, courseViewModel, loggedInViewModel);
        GoToCourseInputBoundary goToCourseInteractor = new GoToCourseInteractor(goToCourseOutputBoundary, fileUserDataAccessObject);
        return new LoggedIncontroller(goToCourseInteractor);
    }


}
