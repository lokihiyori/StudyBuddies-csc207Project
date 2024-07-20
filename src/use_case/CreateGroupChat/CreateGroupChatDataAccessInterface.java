package usecase.CreateGroupChat;

import entity.GroupChat;

public interface CreateGroupChatDataAccessInterface {
    public void saveGroupChat(GroupChat groupChat);
    public GroupChat getGroupChat(String code);
    boolean existsByCode(String identifier);
}
