package interface_adapter;

public class LoginState {
    private final String status;
    private final String message;

    public LoginState(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void setUsername(String username) {
    }

    public void setPassword(String password) {
    }
}
