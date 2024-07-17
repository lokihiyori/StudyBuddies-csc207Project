package interface_adapter.GotoGroupChat;

import entity.Users;

public class GotoGroupChatState {
    private Users users;

    public GotoGroupChatState(GotoGroupChatState copy) {
        username = copy.username;
    }
    public GotoGroupChatState(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
