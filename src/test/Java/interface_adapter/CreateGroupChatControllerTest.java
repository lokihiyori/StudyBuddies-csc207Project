package interface_adapter;

import interface_adapter.CreateGroupChat.CreateGroupChatController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import use_case.CreateGroupChat.CreateGroupChatInputBoundary;
import use_case.CreateGroupChat.CreateGroupChatInputData;

import java.io.IOException;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

class CreateGroupChatControllerTest {

    @Mock
    private CreateGroupChatInputBoundary mockCreateGroupChatInteractor;

    @InjectMocks
    private CreateGroupChatController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecuteCreateGroupChat() throws IOException {
        // Arrange
        String code = "12345";

        // Act
        controller.executeCreateGroupChat(code);

        // Assert
        verify(mockCreateGroupChatInteractor).execute(argThat(inputData ->
                inputData.getCode().equals(code)
        ));
    }

    @Test
    void testExecuteCreateGroupChatThrowsIOException() throws IOException {
        // Arrange
        String code = "error";
        doThrow(IOException.class).when(mockCreateGroupChatInteractor).execute(any(CreateGroupChatInputData.class));

        // Assert
        assertThrows(IOException.class, () -> {
            // Act
            controller.executeCreateGroupChat(code);
        });
    }
}

