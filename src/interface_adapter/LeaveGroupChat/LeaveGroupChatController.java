package interface_adapter.LeaveGroupChat;

import use_case.LeaveGroupChat.LeaveGroupChatInputBoundary;

public class LeaveGroupChatController {
    private final LeaveGroupChatInputBoundary interactor;

    public LeaveGroupChatController(LeaveGroupChatInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void handleLeaveRequest(String userId, String chatId) {
        interactor.leaveGroupChat(userId, chatId);
    }
}

