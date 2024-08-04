package interface_adapter.logged_In;

import interface_adapter.GoToCourse.CourseState;
import interface_adapter.GoToCourse.CourseViewModel;
import interface_adapter.ViewManagerModel;
import use_case.GoToCourse.GoToCourseOutputBoundary;
import use_case.GoToCourse.GoToCourseOutputData;

public class LoggedInPresenter  {
    private final LoggedInViewModel loggedInViewModel;
    private final CourseViewModel courseViewModel;

    private ViewManagerModel viewManagerModel;

    public LoggedInPresenter(LoggedInViewModel loggedInViewModel, CourseViewModel courseViewModel, ViewManagerModel viewManagerModel){
        this.loggedInViewModel = loggedInViewModel;
        this.courseViewModel = courseViewModel;
        this.viewManagerModel = viewManagerModel;
    }

//    public void prepareSuccessView(GoToCourseOutputData user){
//        CourseState goToCourseState= courseViewModel.getState();
//        goToCourseState.setUsername(user.getUsername());
//        this.courseViewModel.setState(goToCourseState);
//        this.courseViewModel.firePropertyChanged();
//        this.viewManagerModel.setActiveView(courseViewModel.getViewName());
//        this.viewManagerModel.firePropertyChanged();
//    }

    public void prepareSuccessView() {
        this.viewManagerModel.setActiveView(loggedInViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

    }
}

