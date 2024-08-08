package use_case.CreateEvent;

import data_access.FileUserDataAccessObject;
import entity.User;
import use_case.Login.LoginUserDataAccessInterface;

public class CreateEventInteractor implements CreateEventInputBoundary{
    final CreateEventOutputBoundary createEventPagePresenter;
    final LoginUserDataAccessInterface userDataAccessObject;
    public CreateEventInteractor(CreateEventOutputBoundary createEventOutputBoundery, LoginUserDataAccessInterface userDataAccessObject){
        this.createEventPagePresenter =  createEventOutputBoundery;
        this.userDataAccessObject = userDataAccessObject;
    }

    @Override
    public void execute(CreateEventInputData createEventPageInputData) {
        User user = userDataAccessObject.get(createEventPageInputData.getUsername());
        CreateEventOutputData outputUser = new CreateEventOutputData(user.getName());
        createEventPagePresenter.prepareSuccessView(outputUser);
    }

    public Object getUserDataAccessObject() {
        return userDataAccessObject;
    }

    public Object getOutputBoundary() {
        return createEventPagePresenter;
    }
}
