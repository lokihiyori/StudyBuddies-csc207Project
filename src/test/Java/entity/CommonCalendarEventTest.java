package entity;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class CommonCalendarEventTest {

    @Test
    public void testEventCreation() {
        // Arrange
        String organizer = "John Doe";
        String eventName = "Team Meeting";
        LocalDate eventDate = LocalDate.of(2024, 8, 15);
        LocalDate eventEndDate = LocalDate.of(2024, 8, 15);
        LocalTime eventTime = LocalTime.of(14, 30);
        LocalTime eventEndTime = LocalTime.of(15, 30);
        String eventLocation = "Conference Room";
        int maxAttendance = 50;
        String eventType = "Meeting";

        // Act
        CommonCalendarEvent event = new CommonCalendarEvent(
                organizer, eventName, eventDate, eventEndDate, eventTime, eventEndTime, eventLocation, maxAttendance, eventType
        );

        // Assert
        assertEquals(organizer, event.getOrganizer());
        assertEquals(eventName, event.getName());
        assertEquals(eventDate, event.getDate());
        assertEquals(eventTime, event.getTime());
        assertEquals(eventEndDate, event.getEventEndDate());
        assertEquals(eventEndTime, event.getEventEndTime());
        assertEquals(eventLocation, event.getLocation());
        assertEquals(maxAttendance, event.getMaxAttendance());
        assertEquals(eventType, event.eventType());
    }

    @Test
    public void testSetEventDescription() {
        // Arrange
        String description = "This is a team meeting to discuss project updates.";
        CommonCalendarEvent event = new CommonCalendarEvent(
                "John Doe", "Team Meeting", LocalDate.of(2024, 8, 15), LocalDate.of(2024, 8, 15),
                LocalTime.of(14, 30), LocalTime.of(15, 30), "Conference Room", 50, "Meeting"
        );

        // Act
        event.setEventDescription(description);

        // Assert
        assertEquals(description, event.getEventDescription());
    }

    @Test
    public void testAddAttendance() {
        // Arrange
        CommonCalendarEvent event = new CommonCalendarEvent(
                "John Doe", "Team Meeting", LocalDate.of(2024, 8, 15), LocalDate.of(2024, 8, 15),
                LocalTime.of(14, 30), LocalTime.of(15, 30), "Conference Room", 50, "Meeting"
        );
        String user = "Jane Smith";

        // Act
        event.addAttendance(user);

        // Assert
        assertTrue(event.getAttendance().contains(user));
    }

    @Test
    public void testInitialAttendanceList() {
        // Arrange
        CommonCalendarEvent event = new CommonCalendarEvent(
                "John Doe", "Team Meeting", LocalDate.of(2024, 8, 15), LocalDate.of(2024, 8, 15),
                LocalTime.of(14, 30), LocalTime.of(15, 30), "Conference Room", 50, "Meeting"
        );

        // Act
        ArrayList<String> attendanceList = event.getAttendance();

        // Assert
        assertNotNull(attendanceList);
        assertTrue(attendanceList.isEmpty());
    }
}
