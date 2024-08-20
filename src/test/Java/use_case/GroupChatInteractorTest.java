package use_case;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import use_case.GroupChat.GroupChatInputData;
import use_case.GroupChat.GroupChatInteractor;
import use_case.GroupChat.GroupChatOutputBoundary;
import use_case.GroupChat.GroupChatOutputData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GroupChatInteractorTest {

    private GroupChatInteractor interactor;
    private GroupChatOutputBoundary outputBoundary;

    @BeforeEach
    void setUp() {
        outputBoundary = Mockito.mock(GroupChatOutputBoundary.class);
        interactor = new GroupChatInteractor(outputBoundary);
    }

    @Test
    void testSendMessage() {
        // Given
        GroupChatInputData inputData = new GroupChatInputData("Hello, World!", "Alice");

        // When
        interactor.sendMessage(inputData);

        // Then
        ArgumentCaptor<GroupChatOutputData> captor = ArgumentCaptor.forClass(GroupChatOutputData.class);
        verify(outputBoundary).presentMessage(captor.capture());

        GroupChatOutputData capturedOutput = captor.getValue();
        assertEquals("Alice: Hello, World!", capturedOutput.getMessage());
    }
}
