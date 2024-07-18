package interface_adapter.GetMyGroupChat;

import entity.GroupChat;
import entity.Users;

import java.util.List;

public class GetMyGroupChatState {
    private Users users;
    private final List<GroupChat> groupChatList;

    public GetMyGroupChatState(GetMyGroupChatState copy, List<GroupChat> groupChatList) {
        users = copy.users;
        this.groupChatList = getGroupChatList();
    }

    public List<GroupChat> getGroupChatList() {
        return groupChatList;
    }
    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
