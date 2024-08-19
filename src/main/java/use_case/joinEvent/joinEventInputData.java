package use_case.joinEvent;

/**
 * The JoinEventInputData class is a data structure that holds the necessary
 * information required for a user to join an event. This includes the username
 * of the user and the name of the event they wish to join.
 */
public class joinEventInputData {
    final private String username;
    final private String eventName;

    /**
     * Constructs a new JoinEventInputData instance with the specified event name
     * and username.
     *
     * @param eventName the name of the event the user wishes to join.
     * @param username the username of the user joining the event.
     */
    public joinEventInputData(String eventName,String username) {
        this.username = username;
        this.eventName = eventName;
    }

    /**
     * Returns the username of the user joining the event.
     *
     * @return the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the name of the event the user wishes to join.
     *
     * @return the event name.
     */
    public String getEventName() {
        return eventName;
    }
}
