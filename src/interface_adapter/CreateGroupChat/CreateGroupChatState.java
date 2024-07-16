package interface_adapter.CreateGroupChat;

import entity.Users;

import java.util.List;

public class CreateGroupChatState {
    private String code;
    private List<Users> userlist;
    public CreateGroupChatState(CreateGroupChatState copy){
        code =copy.code;
        userlist = copy.userlist;
    }
    public CreateGroupChatState(){}

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Users> getUserlist() {
        return userlist;
    }

    public void setUserlist(List<Users> userlist) {
        userlist = userlist;
    }
}




