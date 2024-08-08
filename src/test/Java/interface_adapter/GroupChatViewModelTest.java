package interface_adapter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.*;

class GroupChatViewModelTest {

    private GroupChatViewModel viewModel;
    private TestPropertyChangeListener listener;

    @BeforeEach
    void setUp() {
        viewModel = new GroupChatViewModel();
        listener = new TestPropertyChangeListener();
        viewModel.addPropertyChangeListener(listener);
    }

    @Test
    void testJoinGroupChat() {
        // Act
        viewModel.joinGroupChat("CSC207");

        // Assert
        assertTrue(listener.isEventReceived());
        assertEquals("groupChatJoined", listener.getEvent().getPropertyName());
        assertEquals("CSC207", listener.getEvent().getNewValue());
    }

    @Test
    void testAddPropertyChangeListener() {
        // Act
        viewModel.joinGroupChat("CSC207");

        // Assert
        assertTrue(listener.isEventReceived());
    }

    private static class TestPropertyChangeListener implements PropertyChangeListener {
        private PropertyChangeEvent event;
        private boolean eventReceived = false;

        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            this.event = evt;
            this.eventReceived = true;
        }

        public PropertyChangeEvent getEvent() {
            return event;
        }

        public boolean isEventReceived() {
            return eventReceived;
        }
    }
}
