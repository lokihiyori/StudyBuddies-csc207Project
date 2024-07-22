package use_case.LeaveGroupChat;

import entity.GroupChat;
import entity.GroupChatFactory;
import use_case.CreateGroupChat.*;

public class LeaveGroupChatInteractor implements LeaveGroupChatInputBoundary {
    private final LeaveGroupChatDataAccessInterface dataAccess;
    private final LeaveGroupChatOutputBoundary output;

    public LeaveGroupChatInteractor(LeaveGroupChatDataAccessInterface dataAccess, LeaveGroupChatOutputBoundary output) {
        this.dataAccess = dataAccess;
        this.output = output;
    }

    @Override
    public void leaveGroupChat(String userId, String chatId) {
        try {
            dataAccess.removeUserFromGroupChat(userId, chatId);
            output.onLeaveSuccess("User left the chat successfully.");
        } catch (Exception e) {
            output.onLeaveFailure("Failed to leave chat: " + e.getMessage());
        }
    }
}