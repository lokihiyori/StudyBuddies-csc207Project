package interface_adapter;

import interface_adapter.CreateEvent.CreateEventState;
import interface_adapter.CreateEvent.CreateEventViewModel;
import interface_adapter.GoToCourse.CoursePresenter;
import interface_adapter.GoToCourse.CourseState;
import interface_adapter.GoToCourse.CourseViewModel;
import interface_adapter.logged_In.LoggedInViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import use_case.CreateEvent.CreateEventOutputData;
import use_case.GoToCourse.GoToCourseOutputData;
import use_case.joinEvent.joinEventOutputData;

import static org.mockito.Mockito.*;

class CoursePresenterTest {

    @InjectMocks
    private CoursePresenter presenter;

    @Mock
    private CourseViewModel mockCourseViewModel;
    @Mock
    private LoggedInViewModel mockLoggedInViewModel;
    @Mock
    private CreateEventViewModel mockCreateEventViewModel;
    @Mock
    private ViewManagerModel mockViewManagerModel;
    @Mock
    private CreateEventState mockCreateEventState;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(mockCreateEventViewModel.getState()).thenReturn(mockCreateEventState);
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

    @Test
    void testPrepareSuccessViewCreateEvent() {
        // Arrange
        CreateEventOutputData eventData = new CreateEventOutputData("username");
        when(mockCreateEventViewModel.getViewName()).thenReturn("CreateEventView");

        // Act
        presenter.prepareSuccessView(eventData);

        // Assert
        verify(mockCreateEventState).setUsername("username");
        verify(mockCreateEventViewModel).setState(mockCreateEventState);
        verify(mockCreateEventViewModel).firePropertyChanged();
        verify(mockViewManagerModel).setActiveView("CreateEventView");
        verify(mockViewManagerModel).firePropertyChanged();
    }


    @Test
    void testPrepareSuccessViewGoToCourse() {
        // Arrange
        GoToCourseOutputData courseData = new GoToCourseOutputData("username");
        CourseState expectedState = new CourseState();
        expectedState.setUsername(courseData.getUsername());

        when(mockCourseViewModel.getViewName()).thenReturn("CourseView");
        when(mockCourseViewModel.getState()).thenReturn(new CourseState());  // Ensure getState does not return null

        // Act
        presenter.prepareSuccessView(courseData);

        // Assert
        verify(mockCourseViewModel).setState(refEq(expectedState));
        verify(mockCourseViewModel).firePropertyChanged();
        verify(mockViewManagerModel).setActiveView("CourseView");
        verify(mockViewManagerModel).firePropertyChanged();
    }


    @Test
    void testPrepareJoinEventSuccessView() {
        // Arrange
        joinEventOutputData eventData = new joinEventOutputData("event123");
        when(mockCourseViewModel.getViewName()).thenReturn("CourseView");

        // Act
        presenter.prepareJoinEventSuccessView(eventData);

        // Assert
        verify(mockViewManagerModel).setActiveView("CourseView");
        verify(mockViewManagerModel).firePropertyChanged();
    }
}
