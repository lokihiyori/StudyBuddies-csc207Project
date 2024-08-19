package entity;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * A factory interface for creating instances of {@link CalendarEvent}.
 * Implementations of this interface are responsible for providing concrete
 * methods to create different types of calendar events.
 */
public interface CalendarEventFactory {

    /**
     * Creates a new {@link CalendarEvent} with the specified details.
     *
     * @param name          the name of the event
     * @param date          the start date of the event
     * @param endDate       the end date of the event
     * @param time          the start time of the event
     * @param endTime       the end time of the event
     * @param organizer     the organizer of the event
     * @param maxAttendance the maximum number of attendees allowed
     * @param eventType     the type of the event (e.g., lecture, meeting, etc.)
     * @param location      the location of the event
     * @return              a new instance of {@link CalendarEvent} with the specified details
     */
    CalendarEvent create(String name, LocalDate date, LocalDate endDate, LocalTime time, LocalTime endTime,
                         String organizer, int maxAttendance, String eventType, String location);
}
