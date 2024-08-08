package interface_adapter;

import interface_adapter.SignUp.SignUpState;
import interface_adapter.SignUp.SignUpViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SignUpViewModelTest {

    private SignUpViewModel signUpViewModel;
    private PropertyChangeListener mockListener;

    @BeforeEach
    void setUp() {
        signUpViewModel = new SignUpViewModel();
        mockListener = mock(PropertyChangeListener.class);
    }

    @Test
    void testSetState() {
        SignUpState state = new SignUpState();
        state.setUsername("testUser");
        signUpViewModel.setState(state);

        assertEquals("testUser", signUpViewModel.getState().getUsername());
    }

    @Test
    void testFirePropertyChanged() {
        signUpViewModel.addPropertyChangeListener(mockListener);

        SignUpState state = new SignUpState();
        state.setUsername("testUser");
        signUpViewModel.setState(state);
        signUpViewModel.firePropertyChanged();

        ArgumentCaptor<PropertyChangeEvent> eventCaptor = ArgumentCaptor.forClass(PropertyChangeEvent.class);
        verify(mockListener, times(1)).propertyChange(eventCaptor.capture());
        PropertyChangeEvent event = eventCaptor.getValue();

        assertEquals("state", event.getPropertyName());
        assertNull(event.getOldValue());
        assertEquals(state, event.getNewValue());
    }

    @Test
    void testAddPropertyChangeListener() {
        signUpViewModel.addPropertyChangeListener(mockListener);
        signUpViewModel.firePropertyChanged();

        verify(mockListener, times(1)).propertyChange(any(PropertyChangeEvent.class));
    }

    @Test
    void testGetState() {
        SignUpState state = new SignUpState();
        state.setUsername("testUser");
        signUpViewModel.setState(state);

        assertEquals(state, signUpViewModel.getState());
    }
}
