package use_case.MakeEvent;

import entity.CalendarEvent;

public interface makeEventDataAccessInterface {
    boolean existsByName(String identifier);

    void save(CalendarEvent calendarEvent);
    void addParticipant(String eventName, String username);
}
