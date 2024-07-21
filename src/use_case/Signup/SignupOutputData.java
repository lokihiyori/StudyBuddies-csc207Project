package use_case.Signup;

public class SignupOutputData {

    private final String username;
    private final String email;
    private String creationTime;

    private boolean useCaseFailed;

    public SignupOutputData(String username, String creationTime, String email, boolean useCaseFailed) {
        this.username = username;
        this.creationTime = creationTime;
        this.email = email;
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