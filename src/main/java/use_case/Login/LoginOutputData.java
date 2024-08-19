package use_case.Login;

/**
 * The LoginOutputData class is a data structure that holds the information
 * related to the output of the login process. This includes the username of the user
 * and a flag indicating whether the login use case failed.
 */
public class LoginOutputData {
    private final String username;
    private boolean useCaseFailed;

    /**
     * Constructs a new LoginOutputData instance with the specified username and
     * use case failure status.
     *
     * @param username the username of the user who attempted to log in.
     * @param useCaseFailed a flag indicating whether the login use case failed.
     */
    public LoginOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }

    /**
     * Returns the username of the user who attempted to log in.
     *
     * @return the username.
     */
    public String getUsername() {
        return username;
    }
}

