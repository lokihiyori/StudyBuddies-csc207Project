package usecase.GetMyGroupChat;

import entity.GroupChat;
import entity.Users;

import java.util.List;

public class GetMyGroupChatOutputData {
    private Users users;
    private final List<GroupChat> groupChatList;

    public GetMyGroupChatOutputData(Users users, List<GroupChat> groupChatList){
        this.users = users;
        this.groupChatList = groupChatList;
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
