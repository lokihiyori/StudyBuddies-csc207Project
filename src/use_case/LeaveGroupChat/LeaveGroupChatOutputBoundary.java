package use_case.LeaveGroupChat;

public interface LeaveGroupChatOutputBoundary {
        void onLeaveSuccess(String message);
        void onLeaveFailure(String error);
}