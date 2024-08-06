package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CommonCalendarEventTest {

    private CommonCalendarEvent event;

    @BeforeEach
    public void setUp() {
        ArrayList<String> attendance = new ArrayList<>();
        attendance.add("User1");
        attendance.add("User2");

        event = new CommonCalendarEvent(
                "Organizer",
                "Event Name",
                LocalDate.of(2024, 9, 15),
                LocalTime.of(9, 0),
                "Event Location",
                "Event Description",
                attendance,
                LocalDate.of(2024, 9, 15),
                LocalTime.of(17, 0)
        );
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals("Organizer", event.getOrganizer());
        assertEquals("Event Name", event.getName());
        assertEquals(LocalDate.of(2024, 9, 15), event.getDate());
        assertEquals(LocalTime.of(9, 0), event.getTime());
        assertEquals("Event Location", event.getLocation());
        assertEquals("Event Description", event.getEventDescription());
        assertEquals(LocalDate.of(2024, 9, 15), event.getEventEndDate());
        assertEquals(LocalTime.of(17, 0), event.getEventEndTime());
        assertEquals(2, event.getAttendance().size());
        assertTrue(event.getAttendance().contains("User1"));
        assertTrue(event.getAttendance().contains("User2"));
    }

    @Test
    public void testSetEventDescription() {
        event.setEventDescription("New Description");
        assertEquals("New Description", event.getEventDescription());
    }

    @Test
    public void testAddAttendance() {
        event.addAttendance("User3");
        assertEquals(3, event.getAttendance().size());
        assertTrue(event.getAttendance().contains("User3"));
    }
}
