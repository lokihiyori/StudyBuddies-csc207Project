package interface_adapter;

import interface_adapter.CreateCourse.CreateCourseState;
import interface_adapter.CreateCourse.CreateCourseViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateCourseViewModelTest {

    private CreateCourseViewModel viewModel;
    private PropertyChangeListener listener;

    @BeforeEach
    void setUp() {
        viewModel = new CreateCourseViewModel();
        listener = mock(PropertyChangeListener.class);
        viewModel.addPropertyChangeListener(listener);
    }

    @Test
    void testGetState() {
        CreateCourseState state = new CreateCourseState("Test Course", "TEST123");
        viewModel.setState(state);
        assertEquals(state, viewModel.getState());
    }

    @Test
    void testSetState() {
        CreateCourseState state = new CreateCourseState("Test Course", "TEST123");
        viewModel.setState(state);
        assertEquals(state, viewModel.getState());
    }

    @Test
    void testFirePropertyChanged() {
        CreateCourseState state = new CreateCourseState("Test Course", "TEST123");
        viewModel.setState(state);
        viewModel.firePropertyChanged();

        verify(listener, times(1)).propertyChange(any(PropertyChangeEvent.class));
    }

    @Test
    void testAddPropertyChangeListener() {
        PropertyChangeListener newListener = mock(PropertyChangeListener.class);
        viewModel.addPropertyChangeListener(newListener);

        CreateCourseState state = new CreateCourseState("New Test Course", "NEWTEST123");
        viewModel.setState(state);
        viewModel.firePropertyChanged();

        verify(newListener, times(1)).propertyChange(any(PropertyChangeEvent.class));
    }
}
