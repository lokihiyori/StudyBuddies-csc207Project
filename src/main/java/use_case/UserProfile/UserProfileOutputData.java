package use_case.UserProfile;

public class UserProfileOutputData {
    private final String name;
    private final String email;
    private final String creationTime;

    public UserProfileOutputData(String name, String email, String creationTime) {
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
