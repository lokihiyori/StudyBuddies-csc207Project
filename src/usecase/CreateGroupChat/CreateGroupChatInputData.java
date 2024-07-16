package usecase.CreateGroupChat;

import entity.Users;

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
