package entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
/**
 * Represents a common calendar event that stores details about an event including
 * its name, date, time, location, organizer, and attendance.
 * Implements the CalendarEvent interface.
 */
public class CommonCalendarEvent implements CalendarEvent{
    private String organizer;
    private String eventName;
    private LocalDate eventDate;
    private LocalTime eventTime;
    private int maxAttendance;
    private String eventType;
    private String eventLocation;
    private String eventDescription;
    private ArrayList<String> attendance;
    private LocalDate eventEndDate;
    private LocalTime eventEndTime;
    /**
     * Constructs a new CommonCalendarEvent with the specified details.
     *
     * @param organizer       The organizer of the event.
     * @param eventName       The name of the event.
     * @param eventDate       The start date of the event.
     * @param eventEndDate    The end date of the event.
     * @param eventTime       The start time of the event.
     * @param eventEndTime    The end time of the event.
     * @param eventLocation   The location where the event is held.
     * @param maxAttendance   The maximum number of attendees allowed for the event.
     * @param eventType       The type/category of the event.
     */
    public CommonCalendarEvent(String organizer, String eventName, LocalDate eventDate, LocalDate eventEndDate, LocalTime eventTime, LocalTime eventEndTime, String eventLocation, int maxAttendance, String eventType){

        this.organizer = organizer;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.eventLocation = eventLocation;
        this.maxAttendance = maxAttendance;
        this.eventType = eventType;
        this.attendance = new ArrayList<>();
        this.eventDescription = "";
        this.eventEndDate = eventEndDate;
        this.eventEndTime = eventEndTime;
    }
    /**
     * Returns the name of the event.
     *
     * @return The name of the event.
     */
    @Override
    public String getName() {
        return eventName;
    }

    /**
     * Returns the start date of the event.
     *
     * @return The start date of the event.
     */
    @Override
    public LocalDate getDate() {
        return eventDate;
    }

    /**
     * Returns the start time of the event.
     *
     * @return The start time of the event.
     */
    @Override
    public LocalTime getTime() {
        return eventTime;
    }

    /**
     * Returns the type or category of the event.
     *
     * @return The type/category of the event.
     */
    @Override
    public String eventType() {
        return eventType;
    }

    /**
     * Returns the location where the event is held.
     *
     * @return The event location.
     */
    @Override
    public String getLocation() {
        return eventLocation;
    }

    /**
     * Returns the organizer of the event.
     *
     * @return The event organizer.
     */
    @Override
    public String getOrganizer() {
        return organizer;
    }

    /**
     * Returns the description of the event.
     *
     * @return The event description.
     */
    @Override
    public String getEventDescription() {
        return eventDescription;
    }

    /**
     * Sets the description of the event.
     *
     * @param description The description of the event.
     */
    @Override
    public void setEventDescription(String description) {
        this.eventDescription = description;
    }

    /**
     * Returns a list of attendees for the event.
     *
     * @return A list of attendees.
     */
    @Override
    public ArrayList<String> getAttendance() {
        return attendance;
    }

    /**
     * Adds an attendee to the event.
     *
     * @return  The maximum number of attendance.
     */
    public int getMaxAttendance() {
        return maxAttendance;
    }

    /**
     * Adds an attendee to the event.
     *
     * @param user The name of the attendee to be added.
     */
    @Override
    public void addAttendance(String user) {
        this.attendance.add(user);
    }

    /**
     * Returns the end date of the event.
     *
     * @return The end date of the event.
     */
    @Override
    public LocalDate getEventEndDate() {
        return eventEndDate;
    }

    /**
     * Returns the end time of the event.
     *
     * @return The end time of the event.
     */
    @Override
    public LocalTime getEventEndTime() {
        return eventEndTime;
    }
}
