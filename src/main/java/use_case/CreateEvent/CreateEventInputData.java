package use_case.CreateEvent;

/**
 * The CreateEventInputData class is a data structure that holds the necessary
 * information required to create a new event. Currently, this includes the username
 * of the user who is creating the event.
 */
public class CreateEventInputData {
    final private String username;

    /**
     * Constructs a new CreateEventInputData instance with the specified username.
     *
     * @param username the username of the user creating the event.
     */
    public CreateEventInputData(String username) {
        this.username = username;
    }

    /**
     * Returns the username of the user creating the event.
     *
     * @return the username.
     */
    public String getUsername() {
        return username;
    }
}
