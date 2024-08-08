package interface_adapter;

import interface_adapter.GoToCourse.CourseViewController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import use_case.CreateEvent.CreateEventInputBoundary;
import use_case.CreateEvent.CreateEventInputData;
import use_case.GoToCourse.GoToCourseInputBoundary;
import use_case.joinEvent.joinEventInputBoundary;

import static org.mockito.Mockito.*;

class CourseViewControllerTest {

    @InjectMocks
    private CourseViewController controller;

    @Mock
    private GoToCourseInputBoundary mockGoToCourseInputBoundary;
    @Mock
    private CreateEventInputBoundary mockCreateEventInteractor;
    @Mock
    private joinEventInputBoundary mockJoinEventInputBoundary;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecuteLogOut() {
        // Act
        controller.executeLogOut();

        // Assert
        verify(mockGoToCourseInputBoundary).execute();
        verifyNoMoreInteractions(mockGoToCourseInputBoundary);
        verifyNoInteractions(mockCreateEventInteractor, mockJoinEventInputBoundary);
    }

    @Test
    void testExecuteCreateEvent() {
        // Arrange
        String username = "testUser";
        CreateEventInputData expectedInput = new CreateEventInputData(username);

        // Act
        controller.executeCreateEvent(username);

        // Assert
        verify(mockCreateEventInteractor).execute(refEq(expectedInput));
        verifyNoMoreInteractions(mockCreateEventInteractor);
        verifyNoInteractions(mockGoToCourseInputBoundary, mockJoinEventInputBoundary);
    }
}

