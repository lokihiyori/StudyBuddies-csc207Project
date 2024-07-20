package use_case.Signup;

public class SignupInputData {

    final private String username;
    final private String password;
    final private String repeatPassword;

    public SignupInputData(String username, String password, String repeatPassword) {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

    public String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }
}
