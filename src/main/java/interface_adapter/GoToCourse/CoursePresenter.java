package interface_adapter.GoToCourse;

import interface_adapter.CreateEvent.CreateEventState;
import interface_adapter.CreateEvent.CreateEventViewModel;
import interface_adapter.ViewManagerModel;
import use_case.logOut.LogoOutOutputBoundary;
import interface_adapter.logged_In.LoggedInViewModel;
import use_case.CreateEvent.CreateEventOutputBoundary;
import use_case.CreateEvent.CreateEventOutputData;
import use_case.GoToCourse.GoToCourseOutputBoundary;
import use_case.GoToCourse.GoToCourseOutputData;

import use_case.joinEvent.joinEventOutputBoundary;
import use_case.joinEvent.joinEventOutputData;

public class CoursePresenter implements LogoOutOutputBoundary, GoToCourseOutputBoundary, CreateEventOutputBoundary, joinEventOutputBoundary {
    private final CourseViewModel courseViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private final CreateEventViewModel createEventViewModel;
    private ViewManagerModel viewManagerModel;

    public CoursePresenter(ViewManagerModel viewManagerModel, CourseViewModel courseViewModel, CreateEventViewModel createEventViewModel, LoggedInViewModel loggedInViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.courseViewModel = courseViewModel;
        this.createEventViewModel = createEventViewModel;
        this.loggedInViewModel = loggedInViewModel;
    }
    public void prepareSuccessView() {
        this.viewManagerModel.setActiveView(loggedInViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
}
    public void prepareSuccessView(CreateEventOutputData user){
        CreateEventState createEventState = createEventViewModel.getState();
        createEventState.setUsername(user.getUsername());
        this.createEventViewModel.setState(createEventState);
        this.createEventViewModel.firePropertyChanged();
        this.viewManagerModel.setActiveView(createEventViewModel.getViewName());
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

    @Override
    public void prepareJoinEventSuccessView(joinEventOutputData user) {
        this.viewManagerModel.setActiveView(courseViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
