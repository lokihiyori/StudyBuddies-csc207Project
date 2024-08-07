package data_access;

import entity.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.MakeEvent.makeEventDataAccessInterface;
import use_case.joinEvent.joinEventDataAccessInterface;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileEventDataAccessObjectTest {

    private FileEventDataAccessObject dataAccessObject;
    private Path tempCsvFile;
    private UserFactory userFactory;
    private CalendarEventFactory calendarEventFactory;

    @BeforeEach
    public void setUp() throws IOException {
        // Create a temporary CSV file
        tempCsvFile = Files.createTempFile("events", ".csv");

        // Initialize the factories
        userFactory = new CommonUserFactory(); // Implement as needed
        calendarEventFactory = new CommonCalendarEventFactory(); // Implement as needed

        // Initialize the data access object
        dataAccessObject = new FileEventDataAccessObject(tempCsvFile.toString(), userFactory, calendarEventFactory);
    }

    @AfterEach
    public void tearDown() throws IOException {
        // Delete the temporary file after each test
        Files.deleteIfExists(tempCsvFile);
    }

    @Test
    public void testSaveAndLoadEvent() throws IOException {
        // Arrange
        CalendarEvent event = new CommonCalendarEvent(
                "John Doe", "Team Meeting", LocalDate.of(2024, 8, 15), LocalDate.of(2024, 8, 15),
                LocalTime.of(14, 30), LocalTime.of(15, 30), "Conference Room", 50, "Meeting"
        );
        dataAccessObject.save(event);

        // Act
        CalendarEvent loadedEvent = dataAccessObject.getCalendarEvent("Team Meeting");

        // Assert
        assertNotNull(loadedEvent);
        assertEquals("Team Meeting", loadedEvent.getName());
        assertEquals("John Doe", loadedEvent.getOrganizer());
        assertEquals(LocalDate.of(2024, 8, 15), loadedEvent.getDate());
        assertEquals(LocalTime.of(14, 30), loadedEvent.getTime());
        assertEquals("Conference Room", loadedEvent.getLocation());
        assertEquals(50, loadedEvent.getMaxAttendance());
        assertEquals("Meeting", loadedEvent.eventType());
    }

    @Test
    public void testAddParticipant() throws IOException {
        // Arrange
        CalendarEvent event = new CommonCalendarEvent(
                "John Doe", "Team Meeting", LocalDate.of(2024, 8, 15), LocalDate.of(2024, 8, 15),
                LocalTime.of(14, 30), LocalTime.of(15, 30), "Conference Room", 50, "Meeting"
        );
        dataAccessObject.save(event);

        // Act
        dataAccessObject.addParticipant("Team Meeting", "Jane Smith");

        // Assert
        CalendarEvent updatedEvent = dataAccessObject.getCalendarEvent("Team Meeting");
        assertNotNull(updatedEvent);
        assertTrue(updatedEvent.getAttendance().contains("Jane Smith"));
    }

    @Test
    public void testSaveEmptyFile() throws IOException {
        // Arrange
        CalendarEvent event = new CommonCalendarEvent(
                "John Doe", "Team Meeting", LocalDate.of(2024, 8, 15), LocalDate.of(2024, 8, 15),
                LocalTime.of(14, 30), LocalTime.of(15, 30), "Conference Room", 50, "Meeting"
        );
        dataAccessObject.save(event);

        // Act
        Files.write(tempCsvFile, new byte[0]); // Clear the file

        // Save again
        dataAccessObject.save();

        // Act and Assert
        CalendarEvent reloadedEvent = dataAccessObject.getCalendarEvent("Team Meeting");
        assertNotNull(reloadedEvent);
        assertEquals("Team Meeting", reloadedEvent.getName());
    }

    @Test
    public void testEventNotExist() {
        // Act
        CalendarEvent event = dataAccessObject.getCalendarEvent("Nonexistent Event");

        // Assert
        assertNull(event);
    }
}
