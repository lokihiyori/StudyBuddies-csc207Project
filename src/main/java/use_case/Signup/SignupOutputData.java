package use_case.Signup;

/**
 * The SignupOutputData class represents the data to be provided upon the completion of a signup process.
 * It contains information about the user and the result of the signup operation.
 */
public class SignupOutputData {

    private final String username;
    private final String email;
    private String creationTime;
    public boolean useCaseFailed;

    /**
     * Constructs a new SignupOutputData instance with the specified details.
     *
     * @param username      the username of the signed-up user.
     * @param email         the email address of the signed-up user.
     * @param creationTime  the time when the user was created, represented as a string.
     * @param useCaseFailed a boolean indicating whether the use case failed or not.
     */
    public SignupOutputData(String username, String email, String creationTime, boolean useCaseFailed) {
        this.username = username;
        this.email = email;
        this.creationTime = creationTime;
        this.useCaseFailed = useCaseFailed;
    }

    /**
     * Gets the username of the signed-up user.
     *
     * @return the username of the signed-up user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the creation time of the user.
     *
     * @return the creation time of the user as a string.
     */
    public String getCreationTime() {
        return creationTime;
    }

    /**
     * Gets the email address of the signed-up user.
     *
     * @return the email address of the signed-up user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the creation time of the user.
     *
     * @param creationTime the new creation time to set.
     */
    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

}