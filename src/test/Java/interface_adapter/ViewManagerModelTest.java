package interface_adapter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ViewManagerModelTest {

    private ViewManagerModel viewManagerModel;

    @BeforeEach
    void setUp() {
        viewManagerModel = new ViewManagerModel();
    }

    @Test
    void testSetActiveView() {
        // Arrange
        String expectedViewName = "NewActiveView";

        // Act
        viewManagerModel.setActiveView(expectedViewName);

        // Assert
        assertEquals(expectedViewName, viewManagerModel.getActiveView());
    }

    @Test
    void testFirePropertyChanged() {
        // Arrange
        PropertyChangeListener listener = mock(PropertyChangeListener.class);
        viewManagerModel.addPropertyChangeListener(listener);
        String expectedViewName = "AnotherView";

        // Act
        viewManagerModel.setActiveView(expectedViewName);
        viewManagerModel.firePropertyChanged();

        // Assert
        verify(listener, times(1)).propertyChange(any(PropertyChangeEvent.class));
    }

    @Test
    void testAddPropertyChangeListener() {
        // Arrange
        PropertyChangeListener listener = mock(PropertyChangeListener.class);

        // Act
        viewManagerModel.addPropertyChangeListener(listener);
        viewManagerModel.setActiveView("ViewName");
        viewManagerModel.firePropertyChanged();

        // Assert
        verify(listener, times(1)).propertyChange(any(PropertyChangeEvent.class));
    }
}

