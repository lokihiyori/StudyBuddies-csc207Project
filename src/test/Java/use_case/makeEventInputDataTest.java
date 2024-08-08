package use_case;

import org.junit.jupiter.api.Test;
import use_case.MakeEvent.makeEventInputData;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class makeEventInputDataTest {

    @Test
    void testMakeEventInputData() {
        // Arrange
        String organiserName = "John Doe";
        String eventName = "Tech Conference";
        String location = "Auditorium A";
        LocalDate eventDate = LocalDate.of(2023, 8, 15);
        LocalDate eventEndDate = LocalDate.of(2023, 8, 16);
        LocalTime eventTime = LocalTime.of(10, 0);
        LocalTime eventEndTime = LocalTime.of(16, 0);
        String eventLabel = "Technology";
        String eventType = "Conference";
        int eventMaxAttendance = 100;

        // Act
        makeEventInputData inputData = new makeEventInputData(organiserName, eventName, location, eventDate, eventEndDate, eventTime, eventEndTime, eventLabel, eventType, eventMaxAttendance);

        // Assert
        assertEquals(organiserName, inputData.getOrganiserName());
        assertEquals(eventName, inputData.getEventName());
        assertEquals(location, inputData.getLocation());
        assertEquals(eventDate, inputData.getEventDate());
        assertEquals(eventEndDate, inputData.getEventEndDate());
        assertEquals(eventTime, inputData.getEventTime());
        assertEquals(eventEndTime, inputData.getEventEndTime());
        assertEquals(eventLabel, inputData.getEventLabel());
        assertEquals(eventType, inputData.getEventType());
        assertEquals(eventMaxAttendance, inputData.getEventMaxAttendance());
    }
}
