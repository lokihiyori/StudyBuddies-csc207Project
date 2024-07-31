package interface_adapter.UserProfile;

import use_case.UserProfile.UserProfileInputBoundary;
import use_case.UserProfile.UserProfileOutputBoundary;
import use_case.UserProfile.UserProfileOutputData;

public class UserProfilePresenter implements UserProfileOutputBoundary {
    private UserProfileViewModel viewModel;

    @Override
    public void presentUserProfile(UserProfileOutputData outputData) {
        viewModel = new UserProfileViewModel(
                outputData.getName(),
                outputData.getEmail(),
                outputData.getCreationTime()
        );
    }

    public UserProfileViewModel getViewModel() {
        return viewModel;
    }
}
