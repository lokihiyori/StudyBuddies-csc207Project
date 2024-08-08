package interface_adapter;

import interface_adapter.CreateEvent.CreateEventState;
import interface_adapter.CreateEvent.CreateEventViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.beans.PropertyChangeListener;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

class CreateEventViewModelTest {

    private CreateEventViewModel viewModel;
    private PropertyChangeListener mockListener;

    @BeforeEach
    void setUp() {
        viewModel = new CreateEventViewModel();
        mockListener = mock(PropertyChangeListener.class);
        viewModel.addPropertyChangeListener(mockListener);
    }

    @Test
    void testSetStateFiresPropertyChange() {
        // Arrange
        CreateEventState newState = new CreateEventState();
        newState.setEventType("Sports");

        // Act
        viewModel.setState(newState);

        // Assert
        verify(mockListener).propertyChange(argThat(evt ->
                "state".equals(evt.getPropertyName()) &&
                        evt.getOldValue() != evt.getNewValue() &&
                        evt.getNewValue() == newState
        ));
    }

    @Test
    void testNotifyEventAdded() {
        // Act
        viewModel.notifyEventAdded();

        // Assert
        verify(mockListener).propertyChange(argThat(evt ->
                "eventsUpdated".equals(evt.getPropertyName()) &&
                        Boolean.FALSE.equals(evt.getOldValue()) &&
                        Boolean.TRUE.equals(evt.getNewValue())
        ));
    }

    @Test
    void testFirePropertyChanged() {
        // Act
        viewModel.firePropertyChanged();

        // Assert
        verify(mockListener).propertyChange(argThat(evt ->
                "state".equals(evt.getPropertyName()) &&
                        evt.getOldValue() == null &&
                        evt.getNewValue() == viewModel.getState()
        ));
    }

    @Test
    void testGetState() {
        // Act
        CreateEventState retrievedState = viewModel.getState();

        // Assert
        assertEquals(viewModel.getState(), retrievedState);
    }
}

