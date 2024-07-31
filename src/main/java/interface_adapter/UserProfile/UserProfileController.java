package interface_adapter.UserProfile;

import use_case.UserProfile.UserProfileInputBoundary;
import use_case.UserProfile.UserProfileInputData;
import use_case.UserProfile.UserProfileInteractor;

import java.time.LocalDateTime;
import java.util.List;

public class UserProfileController {
    private final UserProfileInteractor interactor;

    public UserProfileController(UserProfileInteractor interactor) {
        this.interactor = interactor;
    }

    public void createUserProfile(String name, String password, String email, LocalDateTime creationTime, List<String> courseCodes) {
        UserProfileInputData inputData = new UserProfileInputData(name, password, email, creationTime, courseCodes);
        interactor.createUserProfile(inputData);
    }
}
