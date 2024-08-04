package interface_adapter.CreateEvent;

import interface_adapter.ViewManagerModel;
import interface_adapter.logged_In.LoggedInState;
import interface_adapter.logged_In.LoggedInViewModel;

import use_case.Cancel.CancelOutputBoundary;
import use_case.Cancel.CancelOutputData;
import use_case.MakeEvent.makeEventOutputBoundary;

public class  createEventPresenter implements makeEventOutputBoundary, CancelOutputBoundary {
    private final LoggedInViewModel loggedInViewModel;
    private ViewManagerModel viewManagerModel;
    public createEventPresenter(ViewManagerModel viewManagerModel, LoggedInViewModel loggedInViewModel){
        this.viewManagerModel = viewManagerModel;
        this.loggedInViewModel = loggedInViewModel;
    }
    @Override
    public void prepareMakeEventSuccessView() {
        LoggedInState loggedInState = loggedInViewModel.getState();
        this.loggedInViewModel.setState(loggedInState);

        loggedInViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(loggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareMakeEventFailView(String error) {

    }


    @Override
    public void prepareSuccessView(CancelOutputData user) {
        LoggedInState loggedInState = loggedInViewModel.getState();
        this.loggedInViewModel.setState(loggedInState);
        loggedInViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(loggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareSuccessView() {

    }
}
