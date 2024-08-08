package interface_adapter;

import interface_adapter.CreateEvent.createEventPresenter;
import interface_adapter.GoToCourse.CourseState;
import interface_adapter.GoToCourse.CourseViewModel;
import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.mockito.Mockito.*;

class CreateEventPresenterTest {
    @Mock
    private CourseViewModel mockCourseViewModel;
    @Mock
    private ViewManagerModel mockViewManagerModel;

    @InjectMocks
    private createEventPresenter presenter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPrepareMakeEventSuccessView() {
        // Arrange
        CourseState mockState = new CourseState();
        when(mockCourseViewModel.getState()).thenReturn(mockState);
        when(mockCourseViewModel.getViewName()).thenReturn("EventView");

        // Act
        presenter.prepareMakeEventSuccessView();

        // Assert
        verify(mockCourseViewModel).getState();
        verify(mockCourseViewModel).setState(mockState);
        verify(mockCourseViewModel).firePropertyChanged();
        verify(mockViewManagerModel).setActiveView("EventView");
        verify(mockViewManagerModel).firePropertyChanged();
    }

    @Test
    void testPrepareMakeEventFailView() {
        // Arrange
        String error = "Error creating event";

        // Act
        presenter.prepareMakeEventFailView(error);

        // The method is empty in the provided code, so there's nothing specific to assert here.
        // If the method evolves to include functionality such as logging or displaying an error message,
        // appropriate assertions should be added here.
    }

    @Test
    void testPrepareSuccessView() {
        // Arrange
        CourseState mockState = new CourseState();
        when(mockCourseViewModel.getState()).thenReturn(mockState);
        when(mockCourseViewModel.getViewName()).thenReturn("CourseView");

        // Act
        presenter.prepareSuccessView();

        // Assert
        verify(mockCourseViewModel).getState();
        verify(mockCourseViewModel).setState(mockState);
        verify(mockCourseViewModel).firePropertyChanged();
        verify(mockViewManagerModel).setActiveView("CourseView");
        verify(mockViewManagerModel).firePropertyChanged();
    }
}

