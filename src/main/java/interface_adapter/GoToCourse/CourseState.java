package interface_adapter.GoToCourse;

/**
 * Represents the state of a course view, including the username of the current user.
 */
public class CourseState {
    private String username = "";
    /**
     * Constructs a new CourseState by copying the state from another CourseState instance.
     *
     * @param copy the CourseState instance to copy from
     */
    public CourseState(CourseState copy) {
        username = copy.username;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.

    /**
     * Constructs an empty CourseState with default values.
     */
    public CourseState() {}

    /**
     * Gets the username associated with this CourseState.
     *
     * @return the username of the current user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username for this CourseState.
     *
     * @param username the new username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
