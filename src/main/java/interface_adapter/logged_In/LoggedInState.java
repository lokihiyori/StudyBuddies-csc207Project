package interface_adapter.logged_In;

/**
 * Represents the state of a user who is logged in.
 * This class stores user-specific information that is relevant when the user is logged in.
 */
public class LoggedInState {
    private String username = "";

    /**
     * Constructs a new instance of LoggedInState by copying the state from another instance.
     *
     * @param copy the instance to copy from
     */
    public LoggedInState(LoggedInState copy) {
        username = copy.username;
    }

    /**
     * Constructs a new instance of LoggedInState with default values.
     */
    // Because of the previous copy constructor, the default constructor must be explicit.
    public LoggedInState() {}

    /**
     * Gets the username of the logged-in user.
     *
     * @return the username of the logged-in user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the logged-in user.
     *
     * @param username the new username for the logged-in user
     */
    public void setUsername(String username) {
        this.username = username;
    }
}