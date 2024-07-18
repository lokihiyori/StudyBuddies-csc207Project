package interface_adapter.CreateGroupChat;

import entity.Users;

import java.util.List;

public class CreateGroupChatState {
    private String code;
    private List<Users> usersList;
    public CreateGroupChatState(CreateGroupChatState copy){
        code =copy.code;
        usersList = copy.usersList;
    }
    public CreateGroupChatState(){}

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Users> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<Users> usersList) {
        this.usersList = usersList;
    }
}




