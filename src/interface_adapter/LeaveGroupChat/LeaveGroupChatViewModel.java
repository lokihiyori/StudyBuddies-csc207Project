package interface_adapter.LeaveGroupChat;

public class LeaveGroupChatViewModel {
    private final LeaveGroupChatState state;

    public LeaveGroupChatViewModel() {
        this.state = new LeaveGroupChatState();
    }

    public LeaveGroupChatState getState() {
        return state;
    }

    public void updateLoadingState(boolean isLoading) {
        state.setLoading(isLoading);
    }

    public void updateMessage(String message) {
        state.setMessage(message);
    }
}

