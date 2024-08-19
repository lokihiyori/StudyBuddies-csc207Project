package entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
/**
 * Factory class for creating instances of {@link CommonCalendarEvent}.
 * Implements the {@link CalendarEventFactory} interface.
 */
public class CommonCalendarEventFactory implements CalendarEventFactory{
    /**
     * Creates a new {@link CommonCalendarEvent} with the specified parameters.
     *
     * @param name           The name of the event.
     * @param date           The start date of the event.
     * @param endDate        The end date of the event.
     * @param time           The start time of the event.
     * @param endTime        The end time of the event.
     * @param organizer      The organizer of the event.
     * @param maxAttendance  The maximum number of attendees allowed.
     * @param eventType      The type or category of the event.
     * @param location       The location where the event is held.
     * @return               A new instance of {@link CommonCalendarEvent}.
     */
    @Override
    public CalendarEvent create(String name, LocalDate date, LocalDate endDate, LocalTime time, LocalTime endTime, String organizer, int maxAttendance, String eventType, String location) {
        return new CommonCalendarEvent(organizer,
                name,
                date,
                endDate,
                time,
                endTime,
                location,
                maxAttendance,
                eventType
                );
    }
}