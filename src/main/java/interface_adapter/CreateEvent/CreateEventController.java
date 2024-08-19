package interface_adapter.CreateEvent;
import use_case.Cancel.CancelInputBoundary;
import use_case.Cancel.CancelInputData;
import use_case.CreateEvent.CreateEventInputBoundary;
import use_case.CreateEvent.CreateEventInteractor;
import use_case.MakeEvent.makeEventInputBoundary;
import use_case.MakeEvent.makeEventInputData;

import java.time.LocalDate;
import java.time.LocalTime;
/**
 * Controller class responsible for managing event creation and cancellation processes.
 * This class interacts with input boundaries for creating and canceling events.
 */
public class CreateEventController {
    final makeEventInputBoundary makeEventInteractor;
    final CancelInputBoundary cancelInteractor;

    /**
     * Constructs a new CreateEventController with the specified input boundaries.
     *
     * @param makeEventInteractor the input boundary for creating events
     * @param cancelInteractor    the input boundary for canceling events
     */
    public CreateEventController(makeEventInputBoundary makeEventInteractor, CancelInputBoundary cancelInteractor){
        this.makeEventInteractor= makeEventInteractor;
        this.cancelInteractor = cancelInteractor;
    }

    /**
     * Executes the event creation process with the specified details.
     * Creates an instance of {@link makeEventInputData} with the provided parameters
     * and passes it to the make event interactor for processing.
     *
     * @param organiserName       the name of the event organizer
     * @param eventName           the name of the event
     * @param location            the location of the event
     * @param eventDate           the start date of the event
     * @param eventEndDate        the end date of the event
     * @param eventTime           the start time of the event
     * @param eventEndTime        the end time of the event
     * @param eventLabel          the label or description of the event
     * @param eventType           the type of the event
     * @param eventMaxAttendance the maximum number of attendees for the event
     */
    public void executeMakeEvent(String organiserName, String eventName, String location, LocalDate eventDate, LocalDate eventEndDate, LocalTime eventTime, LocalTime eventEndTime, String eventLabel, String eventType, int eventMaxAttendance){
        makeEventInputData MakeEventInputData = new makeEventInputData(organiserName, eventName,  location,  eventDate, eventEndDate, eventTime, eventEndTime, eventLabel, eventType,  eventMaxAttendance);
        makeEventInteractor.execute(MakeEventInputData);
    }

    /**
     * Executes the event cancellation process for the specified user.
     * Creates an instance of {@link CancelInputData} and passes it to the cancel interactor.
     *
     * @param username the username of the user requesting the cancellation
     */
    public void executeCancel(String username){
        CancelInputData cancelInputData = new CancelInputData();
        cancelInteractor.execute();
    }

    /**
     * Returns the input boundary used for creating events.
     *
     * @return the {@link makeEventInputBoundary} instance
     */
    public Object getMakeEventInteractor() {
        return makeEventInteractor;
    }

    /**
     * Returns the input boundary used for canceling events.
     *
     * @return the {@link CancelInputBoundary} instance
     */
    public Object getCancelInteractor() {
        return cancelInteractor;
    }
}
