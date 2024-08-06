package entity;

import java.time.LocalDate;
import java.time.LocalTime;

public interface CalendarEventFactory {

    CalendarEvent create(String name, LocalDate date, LocalDate endDate, LocalTime time, LocalTime endTime,
                         String organizer, int maxAttendance, String eventType, String location);
}
