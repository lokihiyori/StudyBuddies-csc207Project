package use_case.MakeEvent;

import java.time.LocalDate;
import java.time.LocalTime;

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
    public String getOrganiserName(){return organiserName;}
    public String getEventName() {
        return eventName;
    }
    public String getLocation() {return location;}
    public LocalDate getEventDate() {return eventDate;}
    public LocalDate getEventEndDate() {return eventEndDate;}
    public LocalTime getEventTime() {
        return eventTime;
    }
    public LocalTime getEventEndTime() {return eventEndTime;}
    String getEventLabel() {
        return eventLabel;
    }
    public String getEventType() {
        return eventType;
    }
    public int getEventMaxAttendance() {
        return eventMaxAttendance;
    }
}
