package interface_adapter;

import interface_adapter.GroupChat.GroupChatController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import use_case.GroupChat.GroupChatInputBoundary;
import use_case.GroupChat.GroupChatInputData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GroupChatControllerTest {

    private GroupChatController controller;
    private GroupChatInputBoundary inputBoundary;

    @BeforeEach
    void setUp() {
        inputBoundary = Mockito.mock(GroupChatInputBoundary.class);
        controller = new GroupChatController(inputBoundary);
    }

    @Test
    void testHandleSendMessage() {
        // Given
        String message = "Test message";
        String username = "Bob";

        // When
        controller.handleSendMessage(message, username);

        // Then
        ArgumentCaptor<GroupChatInputData> captor = ArgumentCaptor.forClass(GroupChatInputData.class);
        verify(inputBoundary).sendMessage(captor.capture());

        GroupChatInputData capturedInput = captor.getValue();
        assertEquals(message, capturedInput.getMessage());
        assertEquals(username, capturedInput.getUsername());
    }
}
