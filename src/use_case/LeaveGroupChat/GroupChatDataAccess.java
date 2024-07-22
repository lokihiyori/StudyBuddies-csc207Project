package use_case.LeaveGroupChat;

import entity.CommonUser;
import entity.GroupChat;
import entity.GroupChatFactory;

import java.util.ArrayList;

public class GroupChatDataAccess implements LeaveGroupChatDataAccessInterface {
    @Override
    public void removeUserFromGroupChat(String userId, String chatId) {
        GroupChat chat = GroupChatFactory.create(chatId);
        ArrayList<CommonUser> members = chat.getGroupMembers();

        members.removeIf(user -> user.getName().equals(userId));

        chat.setGroupMembers(members);
    }
}
