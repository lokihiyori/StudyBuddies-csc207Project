package use_case.GoToCourse;

/**
 * The GoToCourseOutputData class is a data structure that holds the necessary
 * information related to the output of the course navigation process. This includes
 * the username of the user involved in the course navigation.
 */
public class GoToCourseOutputData {
    private final String username;

    /**
     * Constructs a new GoToCourseOutputData instance with the specified username.
     *
     * @param username the username of the user involved in the course navigation process.
     */
    public GoToCourseOutputData(String username) {
        this.username = username;
    }

    /**
     * Returns the username of the user involved in the course navigation process.
     *
     * @return the username.
     */
    public String getUsername() {
        return username;
    }

}
