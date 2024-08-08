package interface_adapter;

import interface_adapter.logged_In.LoggedInState;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoggedInStateTest {

    @Test
    void testUsernameHandling() {
        LoggedInState state = new LoggedInState();
        assertEquals("", state.getUsername(), "Username should initially be empty.");

        state.setUsername("testUser");
        assertEquals("testUser", state.getUsername(), "Username should match the set value.");
    }


    @Test
    void testCopyConstructor() {
        LoggedInState original = new LoggedInState();
        original.setUsername("copyUser");
        LoggedInState copied = new LoggedInState(original);

        assertEquals("copyUser", copied.getUsername(), "Copied state should have the same username as original.");
    }
}

