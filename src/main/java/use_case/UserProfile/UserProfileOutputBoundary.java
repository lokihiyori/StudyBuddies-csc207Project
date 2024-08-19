package use_case.UserProfile;

/**
 * The output boundary interface for presenting user profile data.
 */
public interface UserProfileOutputBoundary {

    /**
     * Presents the user profile data.
     *
     * @param outputData the data representing the user profile to be presented.
     * @return the same {@link UserProfileOutputData} that was provided.
     */
    UserProfileOutputData presentUserProfile(UserProfileOutputData outputData);
}
