package interface_adapter.CreateGroupChat;

import entity.CommonUser;

import java.util.List;

public class CreateGroupChatState {
    private String code;
    private List<CommonUser> usersList;
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

    public List<CommonUser> getUsersList() {
        return usersList;
    }

    public void setUsersList(List<CommonUser> usersList) {
        this.usersList = usersList;
    }
}




