package interface_adapter;

import interface_adapter.CreateGroupChat.CreateGroupChatState;
import interface_adapter.CreateGroupChat.CreateGroupChatViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.mockito.Mockito.*;

class CreateGroupChatViewModelTest {

    @InjectMocks
    private CreateGroupChatViewModel viewModel;

    @Mock
    private PropertyChangeListener listener;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        viewModel.addPropertyChangeListener(listener);
    }

    @Test
    void testSetStateFiresPropertyChange() {
        // Arrange
        CreateGroupChatState newState = new CreateGroupChatState();
        newState.setCode("12345");

        // Act
        viewModel.setState(newState);

        // Assert
        verify(listener).propertyChange(argThat(event ->
                event.getPropertyName().equals("state") &&
                        event.getOldValue() != event.getNewValue() &&
                        event.getNewValue() == newState
        ));
    }

    @Test
    void testSetStateWithSameObjectDoesNotFirePropertyChange() {
        // Arrange
        CreateGroupChatState sameState = viewModel.getState();  // Get current state

        // Act
        viewModel.setState(sameState);  // Set the same state

        // Assert
        verify(listener, never()).propertyChange(any(PropertyChangeEvent.class));
    }

    @Test
    void testPropertyChangeListenerAdditionAndRemoval() {
        // Arrange
        PropertyChangeListener newListener = mock(PropertyChangeListener.class);
        viewModel.addPropertyChangeListener(newListener);

        // Act - Trigger a change to see if new listener gets notified
        viewModel.setState(new CreateGroupChatState());

        // Assert - Both listeners should receive the change event
        verify(listener, times(1)).propertyChange(any(PropertyChangeEvent.class));
        verify(newListener, times(1)).propertyChange(any(PropertyChangeEvent.class));

        // Act - Remove the new listener and trigger another change
        viewModel.removePropertyChangeListener(newListener);
        viewModel.setState(new CreateGroupChatState());

        // Assert - Only the original listener should receive the new change event
        verify(newListener, times(1)).propertyChange(any(PropertyChangeEvent.class)); // No additional calls after removal
    }
}

