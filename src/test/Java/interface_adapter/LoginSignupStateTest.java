package interface_adapter;

import interface_adapter.LoginSignup.LoginSignupState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class LoginSignupStateTest {

    @Test
    void testDefaultConstructor() {
        LoginSignupState state = new LoginSignupState();
        assertEquals("", state.getUsername(), "Username should be an empty string by default.");
    }

    @Test
    void testSetUsername() {
        LoginSignupState state = new LoginSignupState();
        state.setUsername("user123");
        assertEquals("user123", state.getUsername(), "Username should be set to 'user123'.");
    }

    @Test
    void testCopyConstructor() {
        LoginSignupState originalState = new LoginSignupState();
        originalState.setUsername("originalUser");

        LoginSignupState copiedState = new LoginSignupState(originalState);
        assertEquals("originalUser", copiedState.getUsername(), "Copied state's username should be 'originalUser'.");
    }
}
