package entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Represents a general calendar event, providing methods to manage and retrieve
 * details about the event such as its name, date, time, location, and organizer.
 * Implementing classes should provide concrete implementations for managing these details.
 */
public interface CalendarEvent {
    /**
     * Returns the name of the event.
     *
     * @return the name of the event
     */
    String getName();

    /**
     * Returns the date of the event.
     *
     * @return the date of the event
     */
    LocalDate getDate();

    /**
     * Returns the time of the event.
     *
     * @return the time of the event
     */
    LocalTime getTime();

    /**
     * Returns the type of the event (e.g., lecture, meeting, etc.).
     *
     * @return the type of the event
     */
    String eventType();

    /**
     * Returns the location of the event.
     *
     * @return the location of the event
     */
    String getLocation();

    /**
     * Return the organizer of the event.
     *
     * @return the organizer of the event.
     */
    String getOrganizer();

    /**
     * Returns the description of the event.
     *
     * @return The description of the event.
     */
    String getEventDescription();

    /**
     * Returns the maximum number of attendees allowed for the event.
     *
     * @return the maximum attendance
     */
    int getMaxAttendance();

    /**
     * Sets the description of the event.
     *
     * @param description the description of the event
     */
    void setEventDescription(String description);

    /**
     * Returns a list of attendees who have registered for the event.
     *
     * @return a list of attendees
     */
    ArrayList<String> getAttendance();

    /**
     * Adds an attendee to the event's attendance list.
     *
     * @param user the attendee to be added
     */
    void addAttendance(String user);

    /**
     * Returns the end date of the event.
     *
     * @return the end date of the event
     */
    LocalDate getEventEndDate();
    /**
     * Returns the end time of the event.
     *
     * @return the end time of the event
     */
    LocalTime getEventEndTime();
}
