package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MessageTest {

    private Message message;
    private CommonUser sender;
    private GroupChat receiver;
    private LocalDateTime timestamp;
    private String content;
    private String courseId;

    @BeforeEach
    public void setUp() {
        content = "Hello, this is a test message.";
        sender = new CommonUser("testuser", "password", "Test User", LocalDateTime.now());
        receiver = new GroupChat("Test Group");
        timestamp = LocalDateTime.now();
        courseId = "CS101";
        message = new Message(content, sender, receiver, timestamp, courseId);
    }

    @Test
    public void testGetContent() {
        assertEquals(content, message.getContent());
    }

    @Test
    public void testGetDate() {
        assertEquals(timestamp, message.getdate());
    }

    @Test
    public void testGetSender() {
        assertEquals(sender, message.getSender());
    }
}
