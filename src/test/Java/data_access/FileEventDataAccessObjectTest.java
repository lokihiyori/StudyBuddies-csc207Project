package data_access;

import entity.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class FileEventDataAccessObjectTest {

    private FileEventDataAccessObject dao;
    private UserFactory userFactory;
    private CalendarEventFactory calendarEventFactory;
    private final String csvFilePathEvent = "test_events.csv";

    @BeforeEach
    public void setUp() throws IOException {
        userFactory = new CommonUserFactory();
        calendarEventFactory = new CommonCalendarEventFactory();
        dao = new FileEventDataAccessObject(csvFilePathEvent, userFactory, calendarEventFactory);
    }

    @Test
    public void testSaveEvent() {
        CalendarEvent event = calendarEventFactory.create(
                "Test Event", LocalDate.now(), LocalDate.now().plusDays(1),
                LocalTime.now(), LocalTime.now().plusHours(1),
                "testorganizer", 100, "TestType", "TestLocation");
        dao.save(event);

        CalendarEvent retrievedEvent = dao.getCalendarEvent("Test Event");
        assertNotNull(retrievedEvent);
        assertEquals("Test Event", retrievedEvent.getName());
        assertEquals("testorganizer", retrievedEvent.getOrganizer());
        assertEquals("TestType", retrievedEvent.eventType());
        assertEquals("TestLocation", retrievedEvent.getLocation());
    }

    @Test
    public void testExistsByName() {
        CalendarEvent event = calendarEventFactory.create(
                "Test Event", LocalDate.now(), LocalDate.now().plusDays(1),
                LocalTime.now(), LocalTime.now().plusHours(1),
                "testorganizer", 100, "TestType", "TestLocation");
        dao.save(event);

        assertTrue(dao.existsByName("Test Event"));
        assertFalse(dao.existsByName("Nonexistent Event"));
    }

    @Test
    public void testAddParticipant() {
        CalendarEvent event = calendarEventFactory.create(
                "Test Event", LocalDate.now(), LocalDate.now().plusDays(1),
                LocalTime.now(), LocalTime.now().plusHours(1),
                "testorganizer", 100, "TestType", "TestLocation");
        dao.save(event);

        dao.addParticipant("Test Event", "testuser");
        CalendarEvent retrievedEvent = dao.getCalendarEvent("Test Event");
        assertNotNull(retrievedEvent);
        assertTrue(retrievedEvent.getAttendance().contains("testuser"));
    }

    @Test
    public void testSaveAndRetrieveMultipleEvents() {
        CalendarEvent event1 = calendarEventFactory.create(
                "Test Event 1", LocalDate.now(), LocalDate.now().plusDays(1),
                LocalTime.now(), LocalTime.now().plusHours(1),
                "testorganizer1", 100, "TestType1", "TestLocation1");
        CalendarEvent event2 = calendarEventFactory.create(
                "Test Event 2", LocalDate.now(), LocalDate.now().plusDays(1),
                LocalTime.now(), LocalTime.now().plusHours(1),
                "testorganizer2", 100, "TestType2", "TestLocation2");
        dao.save(event1);
        dao.save(event2);

        CalendarEvent retrievedEvent1 = dao.getCalendarEvent("Test Event 1");
        CalendarEvent retrievedEvent2 = dao.getCalendarEvent("Test Event 2");

        assertNotNull(retrievedEvent1);
        assertNotNull(retrievedEvent2);
        assertEquals("Test Event 1", retrievedEvent1.getName());
        assertEquals("Test Event 2", retrievedEvent2.getName());
    }

    @AfterEach
    public void tearDown() {
        File file = new File(csvFilePathEvent);
        if (file.exists()) {
            file.delete();
        }
    }
}
