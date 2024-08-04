package interface_adapter.GoToCourse;

import entity.Course;
import interface_adapter.ViewManagerModel;
import interface_adapter.logOut.LogoOutOutputBoundary;
import interface_adapter.logged_In.LoggedInViewModel;
import use_case.GoToCourse.GoToCourseOutputBoundary;
import use_case.GoToCourse.GoToCourseOutputData;

public class CoursePresenter implements LogoOutOutputBoundary, GoToCourseOutputBoundary{
    private final CourseViewModel courseViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private ViewManagerModel viewManagerModel;

    public CoursePresenter(ViewManagerModel viewManagerModel, CourseViewModel courseViewModel, LoggedInViewModel loggedInViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.courseViewModel = courseViewModel;
        this.loggedInViewModel = loggedInViewModel;
    }
    public void prepareSuccessView() {
        this.viewManagerModel.setActiveView(loggedInViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
}

    @Override
    public void prepareSuccessView(GoToCourseOutputData user) {
        CourseState courseState = courseViewModel.getState();
        courseState.setUsername(user.getUsername());
        this.courseViewModel.setState(courseState);
        this.courseViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(courseViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

    }
}
