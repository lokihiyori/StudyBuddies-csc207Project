package interface_adapter;

import interface_adapter.UserProfile.UserProfileViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserProfileViewModelTest {

    private UserProfileViewModel viewModel;
    private TestPropertyChangeListener listener;

    @BeforeEach
    void setUp() {
        viewModel = new UserProfileViewModel();
        listener = new TestPropertyChangeListener();
        viewModel.addPropertyChangeListener(listener);
    }

    @Test
    void testSetName() {
        viewModel.setName("John Doe");
        assertEquals("John Doe", listener.getLastEvent().getNewValue());
        assertEquals("name", listener.getLastEvent().getPropertyName());
        assertNull(listener.getLastEvent().getOldValue());
    }

    @Test
    void testSetEmail() {
        viewModel.setEmail("john.doe@example.com");
        assertEquals("john.doe@example.com", listener.getLastEvent().getNewValue());
        assertEquals("email", listener.getLastEvent().getPropertyName());
        assertNull(listener.getLastEvent().getOldValue());
    }

    @Test
    void testSetCreationTime() {
        viewModel.setCreationTime("2023-01-01T00:00");
        assertEquals("2023-01-01T00:00", listener.getLastEvent().getNewValue());
        assertEquals("creationTime", listener.getLastEvent().getPropertyName());
        assertNull(listener.getLastEvent().getOldValue());
    }

    @Test
    void testSetCourseCodes() {
        List<String> courseCodes = Arrays.asList("CSC101", "CSC102");
        viewModel.setCourseCodes(courseCodes);
        assertEquals(courseCodes, listener.getLastEvent().getNewValue());
        assertEquals("courseCodes", listener.getLastEvent().getPropertyName());
        assertNull(listener.getLastEvent().getOldValue());
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

