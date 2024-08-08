package interface_adapter;

import interface_adapter.GoToCourse.CourseViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_In.LoggedInPresenter;
import interface_adapter.logged_In.LoggedInViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class LoggedInPresenterTest {

    private LoggedInPresenter presenter;
    private LoggedInViewModel mockLoggedInViewModel;
    private CourseViewModel mockCourseViewModel;
    private ViewManagerModel mockViewManagerModel;

    @BeforeEach
    void setUp() {
        mockLoggedInViewModel = mock(LoggedInViewModel.class);
        mockCourseViewModel = mock(CourseViewModel.class);
        mockViewManagerModel = mock(ViewManagerModel.class);
        presenter = new LoggedInPresenter(mockLoggedInViewModel, mockCourseViewModel, mockViewManagerModel);
    }

    @Test
    void testPrepareSuccessView() {
        // Arrange
        when(mockLoggedInViewModel.getViewName()).thenReturn("LoggedInView");

        // Act
        presenter.prepareSuccessView();

        // Assert
        verify(mockViewManagerModel).setActiveView("LoggedInView");
        verify(mockViewManagerModel).firePropertyChanged();
    }
}
