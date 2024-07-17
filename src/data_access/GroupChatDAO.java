package data_access;

import entity.GroupChat;

import java.util.List;
import java.util.ArrayList;

public class GroupChatDAO implements GroupChatDataAccessInterface {
    private final List<GroupChat> chats;

    public GroupChatDAO() {
        this.chats = new ArrayList<>();
    }

    @Override
    public GroupChat findChatById(String chatId) {
        for (GroupChat chat : chats) {
            if (chat.getChatId().equals(chatId)) {
                return chat;
            }
        }
        return null;
    }

    @Override
    public void updateChat(GroupChat chat) {
    }

    public void addChat(GroupChat chat) {
        this.chats.add(chat);
    }
}
