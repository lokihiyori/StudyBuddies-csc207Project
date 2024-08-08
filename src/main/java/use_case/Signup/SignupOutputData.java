package use_case.Signup;

public class SignupOutputData {

    private final String username;
    private final String email;
    private String creationTime;

    public boolean useCaseFailed;

    public SignupOutputData(String username, String email, String creationTime, boolean useCaseFailed) {
        this.username = username;
        this.email = email;
        this.creationTime = creationTime;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public String getEmail() {
        return email;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

}