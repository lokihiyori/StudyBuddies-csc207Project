package interface_adapter.CreateEvent;

import interface_adapter.GoToCourse.CourseState;
import interface_adapter.GoToCourse.CourseViewModel;
import interface_adapter.ViewManagerModel;
import use_case.Cancel.CancelOutputBoundary;
import use_case.Cancel.CancelOutputData;
import use_case.CreateEvent.CreateEventOutputBoundary;
import use_case.CreateEvent.CreateEventOutputData;
import use_case.MakeEvent.makeEventOutputBoundary;

import javax.swing.text.View;


/**
 * Presenter class responsible for preparing the view for event creation and cancellation results.
 * Implements both {@link makeEventOutputBoundary} and {@link CancelOutputBoundary} interfaces
 * to handle success and failure scenarios for creating and canceling events.
 */
public class  createEventPresenter implements makeEventOutputBoundary, CancelOutputBoundary {
    private final CourseViewModel courseViewModel;
    private ViewManagerModel viewManagerModel;

    /**
     * Constructs a new createEventPresenter with the specified view manager model and course view model.
     *
     * @param viewManagerModel the view manager model used to control the active view
     * @param courseViewModel  the course view model to update with event creation results
     */
    public createEventPresenter(ViewManagerModel viewManagerModel,
                                CourseViewModel courseViewModel){
        this.viewManagerModel = viewManagerModel;
        this.courseViewModel = courseViewModel;
    }
    /**
     * Prepares the view for a successful event creation result.
     * Updates the course view model's state, notifies the view model of the change, and
     * sets the active view in the view manager model.
     */
    public void prepareMakeEventSuccessView() {
        CourseState courseState = courseViewModel.getState();
        this.courseViewModel.setState(courseState);

        courseViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(courseViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    /**
     * Prepares the view for a failed event creation result by handling the error.
     *
     * @param error the error message to be displayed
     */
    @Override
    public void prepareMakeEventFailView(String error) {
    }
    /**
     * Prepares the view for a successful event cancellation result.
     * Updates the course view model's state, notifies the view model of the change, and
     * sets the active view in the view manager model.
     */
    public void prepareSuccessView() {
        CourseState courseState = courseViewModel.getState();
        this.courseViewModel.setState(courseState);
        courseViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(courseViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }


}

