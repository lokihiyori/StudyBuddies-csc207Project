package interface_adapter.UserProfile;

import use_case.UserProfile.UserProfileInputBoundary;
import use_case.UserProfile.UserProfileOutputBoundary;
import use_case.UserProfile.UserProfileOutputData;

public class UserProfilePresenter implements UserProfileOutputBoundary {
    private final UserProfileViewModel viewModel;

    public UserProfilePresenter(UserProfileViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void presentUserProfile(UserProfileOutputData outputData) {
        viewModel.setName(outputData.getName());
        viewModel.setEmail(outputData.getEmail());
        viewModel.setCreationTime(outputData.getCreationTime());
        viewModel.setCourseCodes(outputData.getCourseCodes());
    }

    public UserProfileViewModel getViewModel() {
        return viewModel;
    }
}
