package interface_adapter.GoToCourse;

import entity.Course;
import interface_adapter.CreateEvent.CreateEventState;
import interface_adapter.CreateEvent.CreateEventViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.logOut.LogoOutOutputBoundary;
import interface_adapter.logged_In.LoggedInViewModel;
import use_case.CreateEvent.CreateEventOutputBoundary;
import use_case.CreateEvent.CreateEventOutputData;
import use_case.GoToCourse.GoToCourseOutputBoundary;
import use_case.GoToCourse.GoToCourseOutputData;

import use_case.MakeEvent.makeEventOutputData;
import use_case.joinEvent.joinEventOutputBoundary;
import use_case.joinEvent.joinEventOutputData;
/**
 * Presenter responsible for managing the interactions between the views and the use cases
 * related to course and event management. Implements various output boundaries to handle
 * the presentation logic for different use cases.
 */
public class CoursePresenter implements LogoOutOutputBoundary, GoToCourseOutputBoundary, CreateEventOutputBoundary, joinEventOutputBoundary {
    private final CourseViewModel courseViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private final CreateEventViewModel createEventViewModel;
    private ViewManagerModel viewManagerModel;

    /**
     * Constructs a CoursePresenter with the specified view models and view manager model.
     *
     * @param viewManagerModel      the ViewManagerModel for managing active views
     * @param courseViewModel       the CourseViewModel for course-related views
     * @param createEventViewModel  the CreateEventViewModel for event creation views
     * @param loggedInViewModel     the LoggedInViewModel for logged-in user views
     */
    public CoursePresenter(ViewManagerModel viewManagerModel, CourseViewModel courseViewModel, CreateEventViewModel createEventViewModel, LoggedInViewModel loggedInViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.courseViewModel = courseViewModel;
        this.createEventViewModel = createEventViewModel;
        this.loggedInViewModel = loggedInViewModel;
    }

    /**
     * Prepares the view for a successful logout, updating the view manager to show the logged-in view.
     */
    public void prepareSuccessView() {
        this.viewManagerModel.setActiveView(loggedInViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
}

    /**
     * Prepares the view for a successful event creation.
     * Updates the CreateEventViewModel with the new event data and sets the active view to the event creation view.
     *
     * @param user the CreateEventOutputData containing event creation details
     */
    public void prepareSuccessView(CreateEventOutputData user){
        CreateEventState createEventState = createEventViewModel.getState();
        createEventState.setUsername(user.getUsername());
        this.createEventViewModel.setState(createEventState);
        this.createEventViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(createEventViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

    }

    /**
     * Prepares the view for a successful course navigation.
     * Updates the CourseViewModel with the course data and sets the active view to the course view.
     *
     * @param user the GoToCourseOutputData containing course navigation details
     */
    @Override
    public void prepareSuccessView(GoToCourseOutputData user) {
        CourseState courseState = courseViewModel.getState();
        courseState.setUsername(user.getUsername());
        this.courseViewModel.setState(courseState);
        this.courseViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(courseViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

    }

    /**
     * Prepares the view for a successful event join.
     * Updates the view manager to show the course view.
     *
     * @param user the joinEventOutputData containing event join details
     */

    @Override
    public void prepareJoinEventSuccessView(joinEventOutputData user) {
        this.viewManagerModel.setActiveView(courseViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
