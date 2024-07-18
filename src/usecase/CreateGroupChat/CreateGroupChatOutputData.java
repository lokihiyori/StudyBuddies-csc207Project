package usecase.CreateGroupChat;

import entity.GroupChat;

public class CreateGroupChatOutputData {
    final private String code;
    final private GroupChat groupChat;
    public CreateGroupChatOutputData(String code, GroupChat groupChat) {
        this.code = code;
        this.groupChat = groupChat;
    }

    public String getCode() {
        return code;
    }

    public GroupChat getGroupChat() {
        return groupChat;
    }
}
