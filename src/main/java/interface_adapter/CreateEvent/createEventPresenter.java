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

public class  createEventPresenter implements makeEventOutputBoundary, CancelOutputBoundary {
    private final CourseViewModel courseViewModel;
    private ViewManagerModel viewManagerModel;

    public createEventPresenter(ViewManagerModel viewManagerModel,
                                CourseViewModel courseViewModel){
        this.viewManagerModel = viewManagerModel;
        this.courseViewModel = courseViewModel;
    }

    public void prepareMakeEventSuccessView() {
        CourseState courseState = courseViewModel.getState();
        this.courseViewModel.setState(courseState);

        courseViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(courseViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareMakeEventFailView(String error) {

    }
    public void prepareSuccessView() {
        CourseState courseState = courseViewModel.getState();
        this.courseViewModel.setState(courseState);
        courseViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(courseViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }


}

