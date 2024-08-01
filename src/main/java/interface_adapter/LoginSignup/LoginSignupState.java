package interface_adapter.LoginSignup;

public class LoginSignupState {
    private String username = "";
    public LoginSignupState(LoginSignupState copy) {username = copy.username;}
    public LoginSignupState(){}
    public String getUsername() {return username;}
    public void setUsername(String username) {this.username= username;}
}
