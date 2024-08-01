package use_case.CreateGroupChat;

import entity.CommonUser;

import java.util.List;

public class CreateGroupChatInputData {
    final private String code;
    public CreateGroupChatInputData(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
