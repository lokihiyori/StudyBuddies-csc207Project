package interface_adapter.UserProfile;

import use_case.UserProfile.UserProfileInputBoundary;
import use_case.UserProfile.UserProfileInputData;

import java.time.LocalDateTime;
import java.util.List;

public class UserProfileController {
    private final UserProfileInputBoundary inputBoundary;

    public UserProfileController(UserProfileInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public void createUserProfile(String name, String password, String email, LocalDateTime creationTime) {
        UserProfileInputData inputData = new UserProfileInputData(name, password, email, creationTime);
        inputBoundary.createUserProfile(inputData);
    }
}
