package data_access;

import entity.GroupChat;

public interface GroupChatDataAccessInterface {
    GroupChat findChatById(String chatId);
    void updateChat(GroupChat chat);
}