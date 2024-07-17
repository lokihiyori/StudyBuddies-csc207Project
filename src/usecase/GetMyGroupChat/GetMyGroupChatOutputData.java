package usecase.GetMyGroupChat;

import entity.GroupChat;
import entity.User;
import entity.Users;

import java.util.List;

public class GetMyGroupChatOutputData {
    private Users users;
    private final List<GroupChat> courses;

    public GetMyGroupChatOutputData(Users users, List<GroupChat> courses){
        this.users = users;
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
