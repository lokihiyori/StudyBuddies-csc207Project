package interface_adapter;

import interface_adapter.GoToCourse.CourseState;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CourseStateTest {

    @Test
    void testDefaultConstructor() {
        // Test that the default constructor initializes username to empty string
        CourseState state = new CourseState();
        assertEquals("", state.getUsername(), "Username should be initialized to an empty string.");
    }

    @Test
    void testCopyConstructor() {
        // Setup original state
        CourseState original = new CourseState();
        original.setUsername("user123");

        // Create a copy
        CourseState copy = new CourseState(original);

        // Test that the copy has the same username
        assertEquals(original.getUsername(), copy.getUsername(), "Copy constructor should copy the username.");
    }

    @Test
    void testSetUsername() {
        // Create state and set username
        CourseState state = new CourseState();
        state.setUsername("newUser");

        // Check setUsername works as expected
        assertEquals("newUser", state.getUsername(), "setUsername should update the username correctly.");
    }
}

