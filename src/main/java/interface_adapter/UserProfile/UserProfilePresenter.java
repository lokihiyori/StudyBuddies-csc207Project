package interface_adapter.UserProfile;

import use_case.UserProfile.UserProfileOutputBoundary;
import use_case.UserProfile.UserProfileOutputData;
/**
 * Presenter for managing user profile data and updating the view model.
 */
public class UserProfilePresenter implements UserProfileOutputBoundary {
    private final UserProfileViewModel viewModel;

    // Constructor that requires a UserProfileViewModel parameter
    /**
     * Constructs a {@code UserProfilePresenter} with the specified {@code UserProfileViewModel}.
     *
     * @param viewModel the view model to be updated with user profile data
     */
    public UserProfilePresenter(UserProfileViewModel viewModel) {
        this.viewModel = viewModel;
    }
    /**
     * Updates the view model with the user profile data from the output data.
     *
     * @param outputData the output data containing user profile information
     * @return the provided {@code UserProfileOutputData} object
     */
    @Override
    public UserProfileOutputData presentUserProfile(UserProfileOutputData outputData) {
        viewModel.setName(outputData.getName());
        viewModel.setEmail(outputData.getEmail());
        viewModel.setCreationTime(outputData.getCreationTime());
        viewModel.setCourseCodes(outputData.getCourseCodes());
        return outputData;
    }
    /**
     * Returns the associated view model.
     *
     * @return the {@code UserProfileViewModel} associated with this presenter
     */
    public UserProfileViewModel getViewModel() {
        return viewModel;
    }
}
