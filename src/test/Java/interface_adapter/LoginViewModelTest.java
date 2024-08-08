package interface_adapter;

import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.beans.PropertyChangeListener;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class LoginViewModelTest {

    private LoginViewModel viewModel;
    private PropertyChangeListener listener;

    @BeforeEach
    void setUp() {
        viewModel = new LoginViewModel();
        listener = mock(PropertyChangeListener.class);
        viewModel.addPropertyChangeListener(listener);
    }

    @Test
    void testDefaultState() {
        LoginState state = viewModel.getState();
        assertEquals("", state.getUsername());
        assertEquals("", state.getPassword());
        assertNull(state.getUsernameError());
        assertNull(state.getPasswordError());
    }

    @Test
    void testSetState() {
        LoginState newState = new LoginState();
        newState.setUsername("testUser");
        newState.setPassword("testPass");
        newState.setUsernameError("username error");
        newState.setPasswordError("password error");

        viewModel.setState(newState);

        assertEquals("testUser", viewModel.getState().getUsername());
        assertEquals("testPass", viewModel.getState().getPassword());
        assertEquals("username error", viewModel.getState().getUsernameError());
        assertEquals("password error", viewModel.getState().getPasswordError());
    }

    @Test
    void testFirePropertyChanged() {
        viewModel.firePropertyChanged();
        verify(listener, times(1)).propertyChange(any());
    }
}
