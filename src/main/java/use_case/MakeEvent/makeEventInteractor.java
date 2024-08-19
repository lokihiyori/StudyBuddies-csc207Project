package use_case.MakeEvent;

import entity.CalendarEvent;
import entity.CalendarEventFactory;
import entity.User;
import use_case.Login.LoginUserDataAccessInterface;

/**
 * The makeEventInteractor class implements the {@link makeEventInputBoundary} interface
 * and is responsible for processing input data related to creating a calendar event.
 * It handles the interaction between the input data, data access objects, and the output boundary.
 */
public class makeEventInteractor implements makeEventInputBoundary{
    final makeEventDataAccessInterface eventDataAccessObject ;
    final makeEventUserDataAccessInterface userDataAccessObject;

    final makeEventOutputBoundary createEventPresenter;
    final CalendarEventFactory calendarEventFactory;

    /**
     * Constructs a new makeEventInteractor instance with the specified dependencies.
     *
     * @param eventDataAccessObject the data access object for event-related operations.
     * @param userDataAccessObject the data access object for user-related operations.
     * @param createEventPresenter the output boundary for preparing success or failure views.
     * @param calendarEventFactory the factory for creating {@link CalendarEvent} instances.
     */
    public makeEventInteractor(makeEventDataAccessInterface eventDataAccessObject, makeEventUserDataAccessInterface userDataAccessObject,
                               makeEventOutputBoundary createEventPresenter,CalendarEventFactory calendarEventFactory) {
        this.eventDataAccessObject = eventDataAccessObject;
        this.userDataAccessObject = userDataAccessObject;
        this.createEventPresenter = createEventPresenter;
        this.calendarEventFactory = calendarEventFactory;
    }

    /**
     * Executes the event creation process using the provided input data.
     * It checks if the event already exists, creates a new event if not,
     * saves it to the data store, and updates the user and event data.
     * The result of the operation is communicated through the output boundary.
     *
     * @param makeEventInputData the data required to create a new calendar event.
     */
    @Override
    public void execute(makeEventInputData makeEventInputData) {
        if (eventDataAccessObject.existsByName(makeEventInputData.getEventName())) {
            createEventPresenter.prepareMakeEventFailView("Event already exists.");
        } else {
            CalendarEvent event = calendarEventFactory.create(makeEventInputData.getEventName(),
                    makeEventInputData.getEventDate(), makeEventInputData.getEventEndDate(),
                    makeEventInputData.getEventTime(), makeEventInputData.getEventEndTime(),
                    makeEventInputData.getOrganiserName(), makeEventInputData.getEventMaxAttendance(),
                    makeEventInputData.getEventType(), makeEventInputData.getLocation());
            eventDataAccessObject.save(event);
            eventDataAccessObject.addParticipant(event.getName(), event.getOrganizer());
            userDataAccessObject.addEvent(event.getName(), event.getOrganizer());
            createEventPresenter.prepareMakeEventSuccessView();
        }

    }

    /**
     * Returns the data access object used for event-related operations.
     *
     * @return the event data access object.
     */
    public Object getFileEventDataAccessObject() {
        return eventDataAccessObject;
    }

    /**
     * Returns the data access object used for user-related operations.
     *
     * @return the user data access object.
     */
    public Object getUserDataAccessObject() {
        return userDataAccessObject;
    }

    /**
     * Returns the factory used to create {@link CalendarEvent} instances.
     *
     * @return the calendar event factory.
     */
    public Object getCalendarEventFactory() {
        return calendarEventFactory;
    }

    /**
     * Returns the output boundary used for preparing success or failure views.
     *
     * @return the output boundary for event creation.
     */
    public Object getOutputBoundary() {
        return createEventPresenter;
    }
}

