package use_case.UserProfile;

/**
 * The UserProfileInputBoundary interface defines the method for creating a user profile.
 */
public interface UserProfileInputBoundary {

    /**
     * Creates a user profile based on the provided input data.
     *
     * @param inputData the data needed to create a user profile.
     */
    void createUserProfile(UserProfileInputData inputData);
}
