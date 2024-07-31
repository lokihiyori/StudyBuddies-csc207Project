package use_case.UserProfile;

import java.time.LocalDateTime;
import java.util.List;

public class UserProfileInputData {
    private final String name;
    private final String password;
    private final String email;
    private final LocalDateTime creationTime;
    private final List<String> courseCodes;

    public UserProfileInputData(String name, String password, String email, LocalDateTime creationTime,List<String> courseCodes) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.creationTime = creationTime;
        this.courseCodes = courseCodes;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail(){
        return email;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public List<String> getCourseCodes() {
        return courseCodes;
    }
}
