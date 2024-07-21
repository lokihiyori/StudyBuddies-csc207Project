package usecase.LeaveGroupChat;

import data_access.GroupChatDAO;
import entity.GroupChat;


public class LeaveGroupChatInteractor implements LeaveGroupChatInputBoundary {
    private final GroupChatDAO dataAccess;
    private final LeaveGroupChatOutputBoundary outputBoundary;

    public LeaveGroupChatInteractor(GroupChatDAO dataAccess, LeaveGroupChatOutputBoundary outputBoundary) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void leaveChat(String chatId, String userId) {
        try {
            GroupChat chat = dataAccess.findChatById(chatId);
            if (chat != null && chat.getMembers().contains(userId)) {
                chat.remove(userId);
                dataAccess.updateChat(chat);
                outputBoundary.onLeaveSuccess(new LeaveGroupChatOutputData(true, "User left the chat successfully."));
            } else {
                throw new IllegalArgumentException("User not in chat or chat does not exist.");
            }
        } catch (Exception e) {
            outputBoundary.onLeaveFailure(new LeaveGroupChatOutputData(false, e.getMessage()));
        }
    }
}
