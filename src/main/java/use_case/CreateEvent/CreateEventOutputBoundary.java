package use_case.CreateEvent;

/**
 * The CreateEventOutputBoundary interface defines the method required for handling
 * the output of the event creation process. Implementations of this interface
 * should provide the logic for preparing the view or response when the event creation
 * process is successful.
 */
public interface CreateEventOutputBoundary {

    /**
     * Prepares the view or response to be shown when the event creation process
     * is successful. Implementations should define how the success information,
     * including the user data, is presented.
     *
     * @param user the data related to the user involved in the event creation process.
     */
    void prepareSuccessView(CreateEventOutputData user);
}
