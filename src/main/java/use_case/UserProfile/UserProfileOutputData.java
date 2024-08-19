package use_case.UserProfile;

import java.util.List;

/**
 * Represents the output data for a user profile.
 */
public class UserProfileOutputData {
    private final String name;
    private final String email;
    private final String creationTime;
    private final List<String> courseCodes;


    /**
     * Constructs a new {@code UserProfileOutputData} instance.
     *
     * @param name         the name of the user.
     * @param email        the email address of the user.
     * @param creationTime the time when the user profile was created.
     * @param courseCodes  the list of course codes associated with the user.
     */
    public UserProfileOutputData(String name, String email, String creationTime, List<String> courseCodes) {
        this.name = name;
        this.email = email;
        this.creationTime = creationTime;
        this.courseCodes = courseCodes;
    }

    /**
     * Returns the name of the user.
     *
     * @return the name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the email address of the user.
     *
     * @return the email address of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the time when the user profile was created.
     *
     * @return the creation time of the user profile.
     */
    public String getCreationTime() {
        return creationTime;
    }

    /**
     * Returns the list of course codes associated with the user.
     *
     * @return the list of course codes.
     */
    public List<String> getCourseCodes() {
        return courseCodes;
    }
}
