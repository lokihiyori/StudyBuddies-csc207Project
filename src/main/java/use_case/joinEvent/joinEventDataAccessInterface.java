package use_case.joinEvent;

/**
 * The JoinEventDataAccessInterface defines the method required for interacting
 * with the data storage system when a user joins an event. Implementations of this
 * interface are responsible for adding a participant to an event in the system.
 */
public interface joinEventDataAccessInterface {

    /**
     * Adds a participant to the specified event in the data storage system.
     * Implementations should handle the logic for associating a user with the event.
     *
     * @param eventname the name of the event the user is joining.
     * @param username the username of the user joining the event.
     */
    void addParticipant(String eventname, String username);

}
