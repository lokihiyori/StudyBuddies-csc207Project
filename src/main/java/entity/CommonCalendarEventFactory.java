package entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class CommonCalendarEventFactory implements CalendarEventFactory{
    @Override
    public CalendarEvent create(String name, LocalDate date, LocalDate endDate, LocalTime time, LocalTime endTime, String organizer, String location) {
        return null;
    }
}