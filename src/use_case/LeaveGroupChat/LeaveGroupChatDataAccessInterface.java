package use_case.LeaveGroupChat;
import entity.GroupChat;
import entity.GroupChatFactory;
import entity.CommonUser;
import java.util.ArrayList;

public interface LeaveGroupChatDataAccessInterface {
    void removeUserFromGroupChat(String userId, String chatId);
}

