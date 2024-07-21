package usecase.LeaveGroupChat;

public class LeaveGroupChatOutputData {
    private final boolean success;
    private final String message;

    public LeaveGroupChatOutputData(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
