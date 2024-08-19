package use_case.joinEvent;

/**
 * The JoinEventOutputBoundary interface defines the method required for handling
 * the output of the event join process. Implementations of this interface
 * should provide the logic for preparing the view or response when a user successfully
 * joins an event.
 */
public interface joinEventOutputBoundary {

    /**
     * Prepares the view or response to be shown when the event join process is successful.
     * Implementations should define how the success information, including user data, is presented.
     *
     * @param user the data related to the user who successfully joined the event.
     */
    void prepareJoinEventSuccessView(joinEventOutputData user);
}
