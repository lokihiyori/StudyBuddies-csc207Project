package interface_adapter.UserProfile;

import use_case.UserProfile.UserProfileInputBoundary;
import use_case.UserProfile.UserProfileInputData;
import use_case.UserProfile.UserProfileInteractor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Controller for managing user profile operations.
 */
public class UserProfileController {
    private final UserProfileInteractor interactor;

    /**
     * Constructs a {@code UserProfileController} with the specified {@code UserProfileInteractor}.
     *
     * @param interactor the interactor to be used for user profile operations
     */
    public UserProfileController(UserProfileInteractor interactor) {
        this.interactor = interactor;
    }
    /**
     * Creates a new user profile with the specified details.
     *
     * @param name          the name of the user
     * @param password      the password for the user account
     * @param email         the email address of the user
     * @param creationTime  the time when the user profile is created
     * @param courseCodes   the list of course codes associated with the user
     */
    public void createUserProfile(String name, String password, String email, LocalDateTime creationTime, List<String> courseCodes) {
        UserProfileInputData inputData = new UserProfileInputData(name, password, email, creationTime, courseCodes);
        interactor.createUserProfile(inputData);
    }
}
