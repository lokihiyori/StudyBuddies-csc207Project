package data_access;

import entity.GroupChat;
import entity.GroupChatFactory;
import entity.User;
import usecase.CreateGroupChat.CreateGroupChatDataAccessInterface;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

