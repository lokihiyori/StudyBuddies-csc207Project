package usecase.LeaveGroupChat;

public interface LeaveGroupChatOutputBoundary {
    void onLeaveSuccess(LeaveGroupChatOutputData outputData);
    void onLeaveFailure(LeaveGroupChatOutputData outputData);
}
