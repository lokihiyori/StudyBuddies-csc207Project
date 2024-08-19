package use_case.joinEvent;

/**
 * The JoinEventOutputData class is a data structure that holds the necessary
 * information related to the output of the event join process. This includes
 * the username of the user who successfully joined the event.
 */
public class joinEventOutputData {
    private final String username;

    /**
     * Constructs a new JoinEventOutputData instance with the specified username.
     *
     * @param username the username of the user who successfully joined the event.
     */
    public joinEventOutputData(String username) {
        this.username = username;
    }

    /**
     * Returns the username of the user who successfully joined the event.
     *
     * @return the username.
     */
    public String getUsername() {
        return username;
    }
}
