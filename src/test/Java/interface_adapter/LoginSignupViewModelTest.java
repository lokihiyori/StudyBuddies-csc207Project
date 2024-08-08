package interface_adapter;

import interface_adapter.LoginSignup.LoginSignupState;
import interface_adapter.LoginSignup.LoginSignupViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class LoginSignupViewModelTest {
    private LoginSignupViewModel viewModel;
    private PropertyChangeListener mockListener;

    @BeforeEach
    void setUp() {
        viewModel = new LoginSignupViewModel();
        mockListener = mock(PropertyChangeListener.class);
        viewModel.addPropertyChangeListener(mockListener);
    }

    @Test
    void testSetState() {
        LoginSignupState newState = new LoginSignupState();
        newState.setUsername("newUser");
        viewModel.setState(newState);
        assertEquals("newUser", viewModel.getState().getUsername(), "State should be updated to 'newUser'.");
    }

    @Test
    void testFirePropertyChanged() {
        viewModel.firePropertyChanged();
        verify(mockListener, times(1)).propertyChange(any(PropertyChangeEvent.class));
    }

    @Test
    void testAddPropertyChangeListener() {
        PropertyChangeListener newListener = mock(PropertyChangeListener.class);
        viewModel.addPropertyChangeListener(newListener);

        viewModel.firePropertyChanged();
        verify(newListener, times(1)).propertyChange(any(PropertyChangeEvent.class));
    }

    @Test
    void testGetState() {
        LoginSignupState initialState = viewModel.getState();
        assertEquals("", initialState.getUsername(), "Initial state username should be empty.");
    }
}

