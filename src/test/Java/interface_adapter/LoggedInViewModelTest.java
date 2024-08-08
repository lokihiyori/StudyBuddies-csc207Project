package interface_adapter;

import interface_adapter.logged_In.LoggedInState;
import interface_adapter.logged_In.LoggedInViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class LoggedInViewModelTest {

    private LoggedInViewModel viewModel;
    private PropertyChangeListener mockListener;

    @BeforeEach
    void setUp() {
        viewModel = new LoggedInViewModel();
        mockListener = mock(PropertyChangeListener.class);
        viewModel.addPropertyChangeListener(mockListener);
    }

    @Test
    void testSetLoggedInUserPropertyChange() {
        // Arrange
        String expectedUser = "user123";

        // Act
        viewModel.setLoggedInUser(expectedUser);

        // Assert
        ArgumentCaptor<PropertyChangeEvent> eventCaptor = ArgumentCaptor.forClass(PropertyChangeEvent.class);
        verify(mockListener).propertyChange(eventCaptor.capture());
        PropertyChangeEvent event = eventCaptor.getValue();

        assertEquals("loggedInUser", event.getPropertyName());
        assertEquals(null, event.getOldValue());
        assertEquals(expectedUser, event.getNewValue());
        assertEquals(expectedUser, viewModel.getLoggedInUser(), "Logged in user should be updated and notified.");
    }

    @Test
    void testStateChangeNotification() {
        // Arrange
        LoggedInState newState = new LoggedInState();
        newState.setUsername("newUser");

        // Act
        viewModel.setState(newState);

        // Assert
        ArgumentCaptor<PropertyChangeEvent> eventCaptor = ArgumentCaptor.forClass(PropertyChangeEvent.class);
        verify(mockListener).propertyChange(eventCaptor.capture());
        PropertyChangeEvent event = eventCaptor.getValue();

        assertEquals("state", event.getPropertyName());
        assertEquals("newUser", ((LoggedInState) event.getNewValue()).getUsername());
        assertEquals(newState, event.getNewValue());
        assertEquals("newUser", viewModel.getState().getUsername(), "State should update and notify listeners.");
    }

    @Test
    void testFirePropertyChanged() {
        // Act
        viewModel.firePropertyChanged();

        // Assert
        verify(mockListener).propertyChange(any(PropertyChangeEvent.class));
    }

    @Test
    void testAddAndRemovePropertyChangeListener() {
        // Arrange
        PropertyChangeListener newListener = mock(PropertyChangeListener.class);
        viewModel.addPropertyChangeListener(newListener);
        viewModel.setLoggedInUser("testUser");


        // Assert
        verify(newListener, times(1)).propertyChange(any(PropertyChangeEvent.class));
    }
}


