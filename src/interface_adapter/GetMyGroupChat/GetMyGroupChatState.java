package interface_adapter.GetMyGroupChat;

import entity.GroupChat;
import entity.Users;

import java.util.List;

public class GetMyGroupChatState {
    private Users users;
    private final List<GroupChat> courses;

    public GetMyGroupChatState(GetMyGroupChatState copy, List<GroupChat> courses) {
        users = copy.users;
        this.courses = courses;
    }

    public List<GroupChat> getCourses() {
        return courses;
    }
    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }
}
