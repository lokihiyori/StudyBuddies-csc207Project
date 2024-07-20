package data_access;

import entity.GroupChat;
import use_case.CreateGroupChat.CreateGroupChatDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

public class GroupChatDataAccessObject implements CreateGroupChatDataAccessInterface {

    private final Map<String, GroupChat> groupChats = new HashMap<>();

    public GroupChatDataAccessObject() {
    }

    @Override
    public void saveGroupChat(GroupChat groupChat) {
        groupChats.put(groupChat.getCode(), groupChat);
    }


    @Override
    public GroupChat getGroupChat(String code) {
        return groupChats.get(code);
    }

    @Override
    public boolean existsByCode(String code) {
        return groupChats.get(code) != null;}
}

