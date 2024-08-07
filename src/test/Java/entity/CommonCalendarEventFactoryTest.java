package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class CommonCalendarEventFactoryTest {

    private CommonCalendarEventFactory factory;

    @BeforeEach
    public void setUp() {
        factory = new CommonCalendarEventFactory();
    }

    @Test
    public void testCreate() {
        String name = "Event Name";
        LocalDate date = LocalDate.of(2024, 9, 15);
        LocalDate endDate = LocalDate.of(2024, 9, 15);
        LocalTime time = LocalTime.of(9, 0);
        LocalTime endTime = LocalTime.of(17, 0);
        String organizer = "Organizer";
        String location = "Event Location";

        CalendarEvent event = factory.create(name, date, endDate, time, endTime, organizer,3, "event", location);

        assertEquals(name, event.getName());
        assertEquals(date, event.getDate());
        assertEquals(endDate, event.getEventEndDate());
        assertEquals(time, event.getTime());
        assertEquals(endTime, event.getEventEndTime());
        assertEquals(organizer, event.getOrganizer());
        assertEquals(location, event.getLocation());
        assertEquals("", event.getEventDescription());
        assertTrue(event.getAttendance().isEmpty());
    }
}
