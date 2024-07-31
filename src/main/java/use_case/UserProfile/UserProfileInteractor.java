package use_case.UserProfile;

import entity.CommonUser;
import entity.User;

public class UserProfileInteractor implements UserProfileInputBoundary {
    private final UserProfileOutputBoundary outputBoundary;

    public UserProfileInteractor(UserProfileOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void createUserProfile(UserProfileInputData inputData) {
        User user = new CommonUser(inputData.getName(), inputData.getPassword(), inputData.getEmail(), inputData.getCreationTime());
        UserProfileOutputData outputData = new UserProfileOutputData(
                user.getName(),
                user.getEmail(),
                user.getCreationTime().toString()
        );
        outputBoundary.presentUserProfile(outputData);
    }
}
