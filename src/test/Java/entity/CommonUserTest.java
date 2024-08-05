package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CommonUserTest {

    private CommonUser user;
    private final String name = "Test User";
    private final String password = "password123";
    private final String email = "test@example.com";
    private final LocalDateTime creationTime = LocalDateTime.now();

    @BeforeEach
    public void setUp() {
        user = new CommonUser(name, password, email, creationTime);
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals(name, user.getName());
        assertEquals(password, user.getPassword());
        assertEquals(email, user.getEmail());
        assertEquals(creationTime, user.getCreationTime());
    }

    @Test
    public void testGroupChatList() {
        List<GroupChat> groupChats = user.getGroupChatList();
        assertNotNull(groupChats);
        assertTrue(groupChats.isEmpty());
    }

    @Test
    public void testGet() {
        assertNull(user.get("someUser"));
    }
}
