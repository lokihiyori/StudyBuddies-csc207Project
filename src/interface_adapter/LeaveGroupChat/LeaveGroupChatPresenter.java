package interface_adapter.LeaveGroupChat;

import use_case.LeaveGroupChat.LeaveGroupChatOutputBoundary;

public class LeaveGroupChatPresenter implements LeaveGroupChatOutputBoundary {
    @Override
    public void onLeaveSuccess(String message) {
        System.out.println(message);
    }

    @Override
    public void onLeaveFailure(String error) {
        System.out.println(error);
    }
}

