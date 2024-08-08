package interface_adapter;

import interface_adapter.CreateEvent.CreateEventState;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateEventStateTest {

    @Test
    void testGettersAndSetters() {
        // Setup
        CreateEventState eventState = new CreateEventState();
        eventState.setUsername("JohnDoe");
        eventState.setPlace("Local Park");
        eventState.setDate(LocalDate.of(2023, 10, 15));
        eventState.setEndDate(LocalDate.of(2023, 10, 16));
        eventState.setTime(LocalTime.of(15, 30));
        eventState.setEndTime(LocalTime.of(17, 30));
        eventState.setEventType("Sports");
        eventState.setMaxplayers(20);
        eventState.setSporttype("Football");
        eventState.setDiscription("Annual football match");

        // Assert
        assertEquals("JohnDoe", eventState.getUsername());
        assertEquals("Local Park", eventState.getPlace());
        assertEquals(LocalDate.of(2023, 10, 15), eventState.getDate());
        assertEquals(LocalDate.of(2023, 10, 16), eventState.getEndDate());
        assertEquals(LocalTime.of(15, 30), eventState.getTime());
        assertEquals(LocalTime.of(17, 30), eventState.getEndTime());
        assertEquals("Sports", eventState.getEventType());
        assertEquals(20, eventState.getMaxplayers());
        assertEquals("Football", eventState.getSporttype());
        assertEquals("Annual football match", eventState.getDiscription());
    }

    @Test
    void testCopyConstructor() {
        // Setup
        CreateEventState original = new CreateEventState();
        original.setUsername("Alice");
        original.setPlace("Community Center");
        original.setDate(LocalDate.of(2023, 12, 1));
        original.setEndDate(LocalDate.of(2023, 12, 2));
        original.setTime(LocalTime.of(10, 0));
        original.setEndTime(LocalTime.of(12, 0));
        original.setEventType("Meeting");
        original.setMaxplayers(50);
        original.setSporttype("Basketball");
        original.setDiscription("Yearly planning meeting");

        // Act
        CreateEventState copy = new CreateEventState(original);

        // Assert
        assertEquals(original.getUsername(), copy.getUsername());
        assertEquals(original.getPlace(), copy.getPlace());
        assertEquals(original.getDate(), copy.getDate());
        assertEquals(original.getEndDate(), copy.getEndDate());
        assertEquals(original.getTime(), copy.getTime());
        assertEquals(original.getEndTime(), copy.getEndTime());
        assertEquals(original.getEventType(), copy.getEventType());
        assertEquals(original.getMaxplayers(), copy.getMaxplayers());
        assertEquals(original.getSporttype(), copy.getSporttype());
        assertEquals(original.getDiscription(), copy.getDiscription());
    }
}
