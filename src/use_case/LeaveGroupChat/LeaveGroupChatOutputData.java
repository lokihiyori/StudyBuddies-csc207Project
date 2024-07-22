package use_case.LeaveGroupChat;

import entity.GroupChat;

public class LeaveGroupChatOutputData {
    final private String code;
    final private GroupChat groupChat;
    public LeaveGroupChatOutputData(String code, GroupChat groupChat) {
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

