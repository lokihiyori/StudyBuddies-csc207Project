package interface_adapter.LeaveGroupChat;

public class LeaveGroupChatState {
    private boolean isLoading;
    private String message;

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean isLoading) {
        this.isLoading = isLoading;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
