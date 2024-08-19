package use_case.MakeEvent;

import entity.CalendarEvent;

/**
 * The makeEventDataAccessInterface defines the methods required for interacting
 * with the data storage system related to calendar events. Implementations of this
 * interface are responsible for checking the existence of events, saving event data,
 * and managing participants for calendar events.
 */
public interface makeEventDataAccessInterface {

    /**
     * Checks if a calendar event with the specified name exists in the data storage system.
     *
     * @param identifier the name of the event to check for existence.
     * @return true if an event with the given name exists, false otherwise.
     */
    boolean existsByName(String identifier);

    /**
     * Saves the specified calendar event to the data storage system.
     * Implementations should handle the logic for persisting the event information.
     *
     * @param calendarEvent the CalendarEvent object to be saved.
     */
    void save(CalendarEvent calendarEvent);

    /**
     * Adds a participant to the specified calendar event.
     * Implementations should handle the logic for updating the event with the new participant.
     *
     * @param eventName the name of the event to which the participant will be added.
     * @param username the username of the participant to be added to the event.
     */
    void addParticipant(String eventName, String username);
}
