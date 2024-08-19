package use_case.CreateEvent;

/**
 * The CreateEventOutputData class is a data structure that holds the necessary
 * information related to the output of the event creation process. This includes
 * the username of the user who is involved in the event creation.
 */
public class CreateEventOutputData {
    private final String username;

    /**
     * Constructs a new CreateEventOutputData instance with the specified username.
     *
     * @param username the username of the user involved in the event creation process.
     */
    public CreateEventOutputData(String username) {
        this.username = username;
    }

    /**
     * Returns the username of the user involved in the event creation process.
     *
     * @return the username.
     */
    public String getUsername() {
        return username;
    }
}
