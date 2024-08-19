package use_case.joinEvent;

/**
 * The JoinEventUserDataAccessInterface defines the method required for interacting
 * with the data storage system to associate an event with a user. Implementations of this
 * interface are responsible for adding an event to a user's list of events in the system.
 */
public interface joinEventUserDataAccessInterface {

    /**
     * Adds the specified event to the user's list of events in the data storage system.
     * Implementations should handle the logic for associating the event with the user.
     *
     * @param eventname the name of the event to be added to the user's list.
     * @param username the username of the user who is joining the event.
     */
    void addEvent(String eventname, String username);
}
