package interface_adapter;

import interface_adapter.UserProfile.UserProfileState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserProfileStateTest {

    private UserProfileState state;
    private TestPropertyChangeListener listener;

    @BeforeEach
    void setUp() {
        state = new UserProfileState();
        listener = new TestPropertyChangeListener();
        state.addPropertyChangeListener(listener);
    }

    @Test
    void testSetName() {
        assertNull(state.getName());
        state.setName("John Doe");
        assertEquals("John Doe", state.getName());
        assertEquals("name", listener.getLastEvent().getPropertyName());
        assertNull(listener.getLastEvent().getOldValue());
        assertEquals("John Doe", listener.getLastEvent().getNewValue());
    }

    @Test
    void testSetEmail() {
        assertNull(state.getEmail());
        state.setEmail("john.doe@example.com");
        assertEquals("john.doe@example.com", state.getEmail());
        assertEquals("email", listener.getLastEvent().getPropertyName());
        assertNull(listener.getLastEvent().getOldValue());
        assertEquals("john.doe@example.com", listener.getLastEvent().getNewValue());
    }

    @Test
    void testSetCreationTime() {
        assertNull(state.getCreationTime());
        state.setCreationTime("2023-01-01T00:00");
        assertEquals("2023-01-01T00:00", state.getCreationTime());
        assertEquals("creationTime", listener.getLastEvent().getPropertyName());
        assertNull(listener.getLastEvent().getOldValue());
        assertEquals("2023-01-01T00:00", listener.getLastEvent().getNewValue());
    }

    @Test
    void testSetCourseCodes() {
        assertNull(state.getCourseCodes());
        List<String> courseCodes = Arrays.asList("CSC101", "CSC102");
        state.setCourseCodes(courseCodes);
        assertEquals(courseCodes, state.getCourseCodes());
        assertEquals("courseCodes", listener.getLastEvent().getPropertyName());
        assertNull(listener.getLastEvent().getOldValue());
        assertEquals(courseCodes, listener.getLastEvent().getNewValue());
    }

    private static class TestPropertyChangeListener implements PropertyChangeListener {

        private PropertyChangeEvent lastEvent;

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            lastEvent = evt;
        }

        public PropertyChangeEvent getLastEvent() {
            return lastEvent;
        }
    }
}

