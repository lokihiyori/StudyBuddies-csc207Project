package interface_adapter;

import interface_adapter.CreateEvent.CreateEventController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import use_case.Cancel.CancelInputBoundary;
import use_case.Cancel.CancelInputData;
import use_case.MakeEvent.makeEventInputBoundary;
import use_case.MakeEvent.makeEventInputData;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.mockito.Mockito.*;

class CreateEventControllerTest {
    @Mock
    private makeEventInputBoundary mockMakeEventInteractor;
    @Mock
    private CancelInputBoundary mockCancelInteractor;

    @InjectMocks
    private CreateEventController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecuteMakeEvent() {
        // Arrange
        String organiserName = "Alice";
        String eventName = "Annual Conference";
        String location = "Convention Center";
        LocalDate eventDate = LocalDate.of(2023, 10, 15);
        LocalDate eventEndDate = LocalDate.of(2023, 10, 16);
        LocalTime eventTime = LocalTime.of(9, 0);
        LocalTime eventEndTime = LocalTime.of(17, 0);
        String eventLabel = "Networking";
        String eventType = "Conference";
        int eventMaxAttendance = 500;

        // Act
        controller.executeMakeEvent(organiserName, eventName, location, eventDate, eventEndDate, eventTime, eventEndTime, eventLabel, eventType, eventMaxAttendance);

        // Assert
        verify(mockMakeEventInteractor).execute(any(makeEventInputData.class));
    }

    @Test
    void testExecuteCancel() {
        // Arrange
        String username = "Bob";

        // Act
        controller.executeCancel(username);

        // Assert
        verify(mockCancelInteractor).execute();
    }
}
