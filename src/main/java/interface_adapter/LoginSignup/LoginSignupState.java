package interface_adapter.LoginSignup;

/**
 * Represents the state for the login/signup view. This class holds the username
 * that is used during the login or signup process.
 */
public class LoginSignupState {
    private String username = "";

    /**
     * Constructs a new instance of LoginSignupState by copying values from another instance.
     *
     * @param copy the instance to copy values from
     */
    public LoginSignupState(LoginSignupState copy) {username = copy.username;}

    /**
     * Constructs a new instance of LoginSignupState with default values.
     */
    public LoginSignupState(){}

    /**
     * Gets the username associated with this state.
     *
     * @return the username
     */
    public String getUsername() {return username;}

    /**
     * Sets the username for this state.
     *
     * @param username the username to set
     */
    public void setUsername(String username) {this.username= username;}
}
