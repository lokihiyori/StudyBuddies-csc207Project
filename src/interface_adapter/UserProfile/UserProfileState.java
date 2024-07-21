package interface_adapter.UserProfile;

public class UserProfileState {
    private UserProfileViewModel userProfileViewModel;

    public UserProfileViewModel getUserProfileViewModel() {
        return userProfileViewModel;
    }

    public void setUserProfileViewModel(UserProfileViewModel userProfileViewModel) {
        this.userProfileViewModel = userProfileViewModel;
    }
}
