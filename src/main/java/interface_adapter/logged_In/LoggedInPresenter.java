package interface_adapter.logged_In;

import interface_adapter.GoToCourse.CourseState;
import interface_adapter.GoToCourse.CourseViewModel;
import interface_adapter.ViewManagerModel;
import use_case.GoToCourse.GoToCourseOutputBoundary;
import use_case.GoToCourse.GoToCourseOutputData;
/**
 * Presenter responsible for managing the view logic when a user is logged in.
 * It coordinates between the view models and the view manager to update the UI state.
 */
public class LoggedInPresenter  {
    private final LoggedInViewModel loggedInViewModel;
    private final CourseViewModel courseViewModel;

    private ViewManagerModel viewManagerModel;
    /**
     * Constructs a new LoggedInPresenter with the specified view models and view manager.
     *
     * @param loggedInViewModel the view model for the logged-in state
     * @param courseViewModel the view model for the course view
     * @param viewManagerModel the view manager model for managing view transitions
     */
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
    /**
     * Prepares the view for a successful login by setting the active view to the logged-in view.
     */
    public void prepareSuccessView() {
        this.viewManagerModel.setActiveView(loggedInViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

    }
}

