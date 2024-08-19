package use_case.GoToCourse;

/**
 * The GoToCourseInputData class is a data structure that holds the necessary
 * information required to navigate to a course. This includes the username
 * of the user who is attempting to access the course.
 */
public class GoToCourseInputData {
    final private String username;

    /**
     * Constructs a new GoToCourseInputData instance with the specified username.
     *
     * @param username the username of the user who is attempting to access the course.
     */
    public GoToCourseInputData(String username) {
        this.username = username;
    }

    /**
     * Returns the username of the user who is attempting to access the course.
     *
     * @return the username.
     */
    public String getUsername() {
        return username;
    }
}
