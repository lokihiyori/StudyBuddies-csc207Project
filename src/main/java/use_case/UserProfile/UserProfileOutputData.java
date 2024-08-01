package use_case.UserProfile;

import java.util.List;

public class UserProfileOutputData {
    private final String name;
    private final String email;
    private final String creationTime;
    private final List<String> courseCodes;


    public UserProfileOutputData(String name, String email, String creationTime, List<String> courseCodes) {
        this.name = name;
        this.email = email;
        this.creationTime = creationTime;
        this.courseCodes = courseCodes;
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

    public List<String> getCourseCodes() {
        return courseCodes;
    }
}
