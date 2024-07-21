package interface_adapter.UserProfile;

import java.util.List;

public class UserProfileViewModel {
    private final String name;
    private final String email;
    private final String creationTime;

    public UserProfileViewModel(String name, String email, String creationTime) {
        this.name = name;
        this.email = email;
        this.creationTime = creationTime;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCreationTime() {
        return creationTime;
    }
}
