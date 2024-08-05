package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class GroupChatTest {

    private GroupChat groupChat;
    private CommonUser user1;
    private CommonUser user2;

    @BeforeEach
    public void setUp() {
        groupChat = new GroupChat("CSC207");
        user1 = new CommonUser("Alice", "password123", "alice@example.com", LocalDateTime.now());
        user2 = new CommonUser("Bob", "password456", "bob@example.com", LocalDateTime.now());
    }

    @Test
    public void testConstructor() {
        assertEquals("CSC207", groupChat.getCode());
        assertTrue(groupChat.getGroupMembers().isEmpty());
    }

    @Test
    public void testSetCode() {
        groupChat.setCode("CSC208");
        assertEquals("CSC208", groupChat.getCode());
    }

    @Test
    public void testSetGroupMembers() {
        ArrayList<CommonUser> members = new ArrayList<>();
        members.add(user1);
        members.add(user2);

        groupChat.setGroupMembers(members);

        assertEquals(members, groupChat.getGroupMembers());
    }

    @Test
    public void testSetMessageHistory() {
        Dictionary<CommonUser, String> history = new Hashtable<>();
        history.put(user1, "Hello from Alice");
        history.put(user2, "Hello from Bob");

        groupChat.setMessageHistory(history);

        assertEquals("Hello from Alice", groupChat.getMessageHistory().get(user1));
        assertEquals("Hello from Bob", groupChat.getMessageHistory().get(user2));
    }

    @Test
    public void testGetCode() {
        assertEquals("CSC207", groupChat.getCode());
    }

    @Test
    public void testGetGroupMembers() {
        ArrayList<CommonUser> members = new ArrayList<>();
        members.add(user1);
        members.add(user2);

        groupChat.setGroupMembers(members);

        assertEquals(members, groupChat.getGroupMembers());
    }

    @Test
    public void testGetMessageHistory() {
        Dictionary<CommonUser, String> history = new Hashtable<>();
        history.put(user1, "Hello from Alice");
        history.put(user2, "Hello from Bob");

        groupChat.setMessageHistory(history);

        assertEquals(history, groupChat.getMessageHistory());
    }
}
