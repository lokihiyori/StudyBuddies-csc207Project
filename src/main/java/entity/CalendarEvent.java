package entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public interface CalendarEvent {
    String getName();
    LocalDate getDate();
    LocalTime getTime();

    String getLocation();
    String getOrganizer();
    String getEventDescription();
    void setEventDescription(String description);
    ArrayList<String> getAttendance();
    void addAttendance(String user);
    LocalDate getEventEndDate();
    LocalTime getEventEndTime();
}
