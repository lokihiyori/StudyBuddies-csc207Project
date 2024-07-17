package usecase.GetMyGroupChat;

import entity.Users;

public class GetMyGroupChatInputData {
    final private Users users;
    public GetMyGroupChatInputData(Users users){
        this.users = users;}

    public String getUsers() {
        return users.getUsername();
    }
}
