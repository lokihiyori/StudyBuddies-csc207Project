package usecase.LeaveGroupChat;

public interface LeaveGroupChatInputBoundary {
    void leaveChat(String chatId, String userId) throws Exception;
}