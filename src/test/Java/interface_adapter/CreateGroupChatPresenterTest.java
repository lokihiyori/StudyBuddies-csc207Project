
package interface_adapter;

import interface_adapter.CreateGroupChat.CreateGroupChatPresenter;
import interface_adapter.CreateGroupChat.CreateGroupChatState;
import interface_adapter.CreateGroupChat.CreateGroupChatViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import use_case.CreateGroupChat.CreateGroupChatOutputData;
import entity.GroupChat;
import entity.CommonUser;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

class CreateGroupChatPresenterTest {

    @Mock
    private CreateGroupChatViewModel mockViewModel;
    @Mock
    private GroupChat mockGroupChat;
    @Mock
    private CommonUser mockUser; // Mocking a CommonUser assuming it's a complex entity.

    @InjectMocks
    private CreateGroupChatPresenter presenter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    void testPrepareSuccessView() {
        // Arrange
        String code = "chat123";
        CreateGroupChatOutputData outputData = new CreateGroupChatOutputData(code, mockGroupChat);

        ArgumentCaptor<CreateGroupChatState> captor = ArgumentCaptor.forClass(CreateGroupChatState.class);

        // Act
        presenter.prepareSuccessView(outputData);

        // Assert
        verify(mockViewModel).setState(captor.capture());
        CreateGroupChatState capturedState = captor.getValue();

        assertEquals(code, capturedState.getCode());
        assertNotNull(capturedState.getUsersList()); // Further assertions can be added as needed.
    }

    @Test
    void testPrepareFailView() {
        // Arrange
        String error = "Failed to create group chat";

        // Act
        presenter.prepareFailView(error);

        // Since there is no functional code for error handling in the provided presenter method,
        // this test serves as a placeholder. Extend this test when the method includes error handling.
    }
}

