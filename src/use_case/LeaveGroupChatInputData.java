package usecase.LeaveGroupChat;

public class LeaveGroupChatInputData {
    private final String chatId;
    private final String userId;

    public LeaveGroupChatInputData(String chatId, String userId) {
        this.chatId = chatId;
        this.userId = userId;
    }

    public String getChatId() {
        return chatId;
    }

    public String getUserId() {
        return userId;
    }
}
