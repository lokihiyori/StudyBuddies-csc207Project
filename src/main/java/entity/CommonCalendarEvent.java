package entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

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
    @Override
    public String getName() {
        return eventName;
    }

    @Override
    public LocalDate getDate() {
        return eventDate;
    }

    @Override
    public LocalTime getTime() {
        return eventTime;
    }

    @Override
    public String eventType() {
        return eventType;
    }

    @Override
    public String getLocation() {
        return eventLocation;
    }

    @Override
    public String getOrganizer() {
        return organizer;
    }

    @Override
    public String getEventDescription() {
        return eventDescription;
    }

    @Override
    public void setEventDescription(String description) {
        this.eventDescription = description;
    }

    @Override
    public ArrayList<String> getAttendance() {
        return attendance;
    }
    public int getMaxAttendance() {
        return maxAttendance;
    }

    @Override
    public void addAttendance(String user) {
        this.attendance.add(user);
    }

    @Override
    public LocalDate getEventEndDate() {
        return eventEndDate;
    }

    @Override
    public LocalTime getEventEndTime() {
        return eventEndTime;
    }
}
