package use_case.CreateEvent;

import entity.User;
import use_case.Login.LoginUserDataAccessInterface;
import data_access.FileUserDataAccessObject;

/**
 * The CreateEventInteractor class implements the CreateEventInputBoundary interface
 * and handles the business logic for creating a new event. It interacts with the
 * user data access object to retrieve user information and communicates with the
 * output boundary to present the result of the event creation process.
 */
public class CreateEventInteractor implements CreateEventInputBoundary{
    final CreateEventOutputBoundary createEventPagePresenter;
    final LoginUserDataAccessInterface userDataAccessObject;

    /**
     * Constructs a new CreateEventInteractor with the specified output boundary
     * and user data access object.
     *
     * @param createEventOutputBoundery the output boundary to handle the result of the event creation process.
     * @param userDataAccessObject the data access object to retrieve user information.
     */
    public CreateEventInteractor(CreateEventOutputBoundary createEventOutputBoundery, LoginUserDataAccessInterface userDataAccessObject){
        this.createEventPagePresenter =  createEventOutputBoundery;
        this.userDataAccessObject = userDataAccessObject;
    }

    /**
     * Executes the event creation process using the provided input data.
     * The process involves retrieving the user information associated with the
     * provided username and preparing the success view through the output boundary.
     *
     * @param createEventPageInputData the data required to create a new event, including the username.
     */
    @Override
    public void execute(CreateEventInputData createEventPageInputData) {
        User user = userDataAccessObject.get(createEventPageInputData.getUsername());
        CreateEventOutputData outputUser = new CreateEventOutputData(user.getName());
        createEventPagePresenter.prepareSuccessView(outputUser);
    }

    /**
     * Returns the user data access object associated with this interactor.
     *
     * @return the LoginUserDataAccessInterface instance used by this interactor.
     */
    public Object getUserDataAccessObject() {
        return userDataAccessObject;
    }

    /**
     * Returns the output boundary associated with this interactor.
     *
     * @return the CreateEventOutputBoundary instance used by this interactor.
     */
    public Object getOutputBoundary() {
        return createEventPagePresenter;
    }
}
