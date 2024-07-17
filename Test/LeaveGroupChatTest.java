import static org.junit.Assert.assertFalse;

import data_access.GroupChatDAO;
import entity.GroupChat;
import entity.Users;
import org.junit.Before;
import org.junit.Test;
import usecase.LeaveGroupChat.LeaveGroupChat;

public class LeaveGroupChatTest {
    private LeaveGroupChat leaveGroupChat;
    private GroupChat chat;
    private Users user;

    @Before
    public void setUp() {
        user = new Users("user1");
        chat = new GroupChat("123");
        chat.addMember(user);
        GroupChatDAO repository = new MockGroupChatRepository(chat);
        leaveGroupChat = new LeaveGroupChat(repository);
    }

    @Test
    public void testUserLeavesGroupChat() {
        leaveGroupChat.leaveChat("123", user);
        assertFalse(chat.getGroupMembers().contains(user));
    }
}
