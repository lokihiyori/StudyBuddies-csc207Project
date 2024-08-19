package use_case.MakeEvent;

/**
 * The makeEventUserDataAccessInterface interface defines the methods for managing user
 * data related to calendar events. This interface provides functionality to update the
 * user data access object with information about the events a user is associated with.
 */
public interface makeEventUserDataAccessInterface {

    /**
     * Adds an event to the user's list of events.
     *
     * @param eventName the name of the event to be added.
     * @param username  the username of the user to whom the event is being added.
     */
    void addEvent(String eventName, String username);
}
