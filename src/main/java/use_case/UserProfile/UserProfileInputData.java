package use_case.UserProfile;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents the data required to create a user profile.
 */
public class UserProfileInputData {
    private final String name;
    private final String password;
    private final String email;
    private final LocalDateTime creationTime;
    private final List<String> courseCodes;

    /**
     * Constructs a UserProfileInputData object with the specified details.
     *
     * @param name          the name of the user.
     * @param password      the password for the user.
     * @param email         the email address of the user.
     * @param creationTime  the time when the profile was created.
     * @param courseCodes   a list of course codes associated with the user.
     */
    public UserProfileInputData(String name, String password, String email, LocalDateTime creationTime,List<String> courseCodes) {
        this.name = name;
        this.password = password;
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
     * Returns the password for the user.
     *
     * @return the password for the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Returns the email address of the user.
     *
     * @return the email address of the user.
     */
    public String getEmail(){
        return email;
    }

    /**
     * Returns the time when the profile was created.
     *
     * @return the creation time of the profile.
     */
    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    /**
     * Returns the list of course codes associated with the user.
     *
     * @return a list of course codes.
     */
    public List<String> getCourseCodes() {
        return courseCodes;
    }
}
