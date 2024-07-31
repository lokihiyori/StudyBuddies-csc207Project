package use_case.CreateGroupChat;

import entity.GroupChat;

import java.io.IOException;

public interface CreateGroupChatDataAccessInterface {
    public void saveGroupChat(GroupChat groupChat) throws IOException;
    public GroupChat getGroupChat(String code);
    boolean existsByCode(String identifier);
}
