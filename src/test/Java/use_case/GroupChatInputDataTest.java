package use_case;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.GroupChat.GroupChatInputData;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GroupChatInputDataTest {

    private GroupChatInputData groupChatInputData;

    @BeforeEach
    void setUp() {
        // Initialize the GroupChatInputData with sample data
        groupChatInputData = new GroupChatInputData("Hello, world!", "Alice");
    }

    @Test
    void testGetMessage() {
        // Test that the message is returned correctly
        assertEquals("Hello, world!", groupChatInputData.getMessage());
    }

    @Test
    void testGetUsername() {
        // Test that the username is returned correctly
        assertEquals("Alice", groupChatInputData.getUsername());
    }
}

