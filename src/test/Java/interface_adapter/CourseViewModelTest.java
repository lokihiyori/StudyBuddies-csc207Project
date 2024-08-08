package interface_adapter;

import interface_adapter.GoToCourse.CourseState;
import interface_adapter.GoToCourse.CourseViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

class CourseViewModelTest {

    private CourseViewModel viewModel;
    private PropertyChangeListener listener;

    @BeforeEach
    void setUp() {
        viewModel = new CourseViewModel();
        listener = mock(PropertyChangeListener.class);
        viewModel.addPropertyChangeListener(listener);
    }


    @Test
    void testGetState() {
        // Arrange
        CourseState state = new CourseState();
        state.setUsername("testUser");
        viewModel.setState(state);

        // Act & Assert
        assertSame(state, viewModel.getState(), "The state returned should be the one that was set.");
    }


    @Test
    void testFirePropertyChanged() {
        // Act
        viewModel.firePropertyChanged();  // This should trigger the property change notification

        // Assert
        verify(listener).propertyChange(any());  // Verify that the listener was indeed notified
    }

    @Test
    void testAddAndRemovePropertyChangeListener() {
        // Arrange
        PropertyChangeListener newListener = mock(PropertyChangeListener.class);
        viewModel.addPropertyChangeListener(newListener);

        // Act - Trigger change to verify both listeners are called
        viewModel.firePropertyChanged();

        // Assert - Both listeners should be notified
        verify(listener).propertyChange(any());
        verify(newListener).propertyChange(any());
    }
}

