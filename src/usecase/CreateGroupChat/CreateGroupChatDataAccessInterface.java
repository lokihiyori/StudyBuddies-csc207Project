package usecase.CreateGroupChat;

import entity.GroupChat;

public interface CreateGroupChatDataAccessInterface {
    public void saveGroupChat(String code);
    public GroupChat getGroupChat(String code);
}
