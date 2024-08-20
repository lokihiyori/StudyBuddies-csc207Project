package use_case;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.GroupChat.GroupChatOutputData;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GroupChatOutputDataTest {

    private GroupChatOutputData groupChatOutputData;

    @BeforeEach
    void setUp() {
        // Initialize GroupChatOutputData with a sample message
        groupChatOutputData = new GroupChatOutputData("Processed message content");
    }

    @Test
    void testGetMessage() {
        // Test that the message is returned correctly
        assertEquals("Processed message content", groupChatOutputData.getMessage());
    }
}

