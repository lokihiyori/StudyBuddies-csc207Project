package interface_adapter;

import interface_adapter.login.LoginState;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoginStateTest {

    @Test
    void testDefaultConstructor() {
        LoginState state = new LoginState();
        assertEquals("", state.getUsername());
        assertNull(state.getUsernameError());
        assertEquals("", state.getPassword());
        assertNull(state.getPasswordError());
    }

    @Test
    void testCopyConstructor() {
        LoginState original = new LoginState();
        original.setUsername("originalUser");
        original.setUsernameError("originalUserError");
        original.setPassword("originalPass");
        original.setPasswordError("originalPassError");

        LoginState copy = new LoginState(original);

        assertEquals("originalUser", copy.getUsername());
        assertEquals("originalUserError", copy.getUsernameError());
        assertEquals("originalPass", copy.getPassword());
        assertEquals("originalPassError", copy.getPasswordError());
    }

    @Test
    void testSettersAndGetters() {
        LoginState state = new LoginState();
        state.setUsername("testUser");
        state.setUsernameError("testUserError");
        state.setPassword("testPass");
        state.setPasswordError("testPassError");

        assertEquals("testUser", state.getUsername());
        assertEquals("testUserError", state.getUsernameError());
        assertEquals("testPass", state.getPassword());
        assertEquals("testPassError", state.getPasswordError());
    }
}
