package entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class CommonCalendarEventFactory implements CalendarEventFactory{
    @Override
    public CalendarEvent create(String name, LocalDate date, LocalDate endDate, LocalTime time, LocalTime endTime, String organizer, int maxAttendance, String evenType, String location) {
        return new CommonCalendarEvent(organizer,
                name,
                date,
                endDate,
                time,
                endTime,
                location,
                maxAttendance,
                evenType
                );
    }
}