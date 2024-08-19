package use_case.MakeEvent;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The makeEventInputData class encapsulates the data required to create or manage
 * a calendar event. It includes details such as the event's organizer, name, location,
 * dates, times, label, type, and maximum attendance.
 */
public class makeEventInputData {

    private final String organiserName;
    private final String eventName;
    private final String location;
    private final LocalDate eventDate;
    private final LocalDate eventEndDate;
    private final LocalTime eventTime;
    private final LocalTime eventEndTime;
    private final String eventLabel;
    private final String eventType;
    private final int eventMaxAttendance;

    /**
     * Constructs a new makeEventInputData instance with the specified details.
     *
     * @param organiserName the name of the person or entity organizing the event.
     * @param eventName the name of the event.
     * @param location the location where the event will take place.
     * @param eventDate the start date of the event.
     * @param eventEndDate the end date of the event.
     * @param eventTime the start time of the event.
     * @param eventEndTime the end time of the event.
     * @param eventLabel a label or category associated with the event.
     * @param eventType the type or nature of the event.
     * @param eventMaxAttendance the maximum number of attendees allowed for the event.
     */
    public makeEventInputData(String organiserName, String eventName, String location, LocalDate eventDate, LocalDate eventEndDate, LocalTime eventTime, LocalTime eventEndTime, String eventLabel, String eventType, int eventMaxAttendance) {
        this.organiserName = organiserName;
        this.eventName = eventName;
        this.location = location;
        this.eventDate = eventDate;
        this.eventEndDate = eventEndDate;
        this.eventTime = eventTime;
        this.eventEndTime = eventEndTime;
        this.eventLabel = eventLabel;
        this.eventType = eventType;
        this.eventMaxAttendance = eventMaxAttendance;
    }

    /**
     * Returns the name of the person or entity organizing the event.
     *
     * @return the organizer's name.
     */
    public String getOrganiserName(){return organiserName;}

    /**
     * Returns the name of the event.
     *
     * @return the event name.
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * Returns the location where the event will take place.
     *
     * @return the event location.
     */
    public String getLocation() {return location;}

    /**
     * Returns the start date of the event.
     *
     * @return the event start date.
     */
    public LocalDate getEventDate() {return eventDate;}

    /**
     * Returns the end date of the event.
     *
     * @return the event end date.
     */
    public LocalDate getEventEndDate() {return eventEndDate;}

    /**
     * Returns the start time of the event.
     *
     * @return the event start time.
     */
    public LocalTime getEventTime() {
        return eventTime;
    }

    /**
     * Returns the end time of the event.
     *
     * @return the event end time.
     */
    public LocalTime getEventEndTime() {return eventEndTime;}

    /**
     * Returns the label or category associated with the event.
     *
     * @return the event label.
     */
    public String getEventLabel() {
        return eventLabel;
    }

    /**
     * Returns the type or nature of the event.
     *
     * @return the event type.
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * Returns the maximum number of attendees allowed for the event.
     *
     * @return the maximum attendance.
     */
    public int getEventMaxAttendance() {
        return eventMaxAttendance;
    }
}
