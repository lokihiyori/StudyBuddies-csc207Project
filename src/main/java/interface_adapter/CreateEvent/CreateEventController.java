package interface_adapter.CreateEvent;
import use_case.Cancel.CancelInputBoundary;
import use_case.Cancel.CancelInputData;
import use_case.CreateEvent.CreateEventInputBoundary;
import use_case.CreateEvent.CreateEventInteractor;
import use_case.MakeEvent.makeEventInputBoundary;
import use_case.MakeEvent.makeEventInputData;

import java.time.LocalDate;
import java.time.LocalTime;

public class CreateEventController {
    final makeEventInputBoundary makeEventInteractor;
    final CancelInputBoundary cancelInteractor;

    public CreateEventController(makeEventInputBoundary makeEventInteractor, CancelInputBoundary cancelInteractor){
        this.makeEventInteractor= makeEventInteractor;
        this.cancelInteractor = cancelInteractor;
    }
    public void executeMakeEvent(String organiserName, String eventName, String location, LocalDate eventDate, LocalDate eventEndDate, LocalTime eventTime, LocalTime eventEndTime, String eventLabel, String eventType, int eventMaxAttendance){
        makeEventInputData MakeEventInputData = new makeEventInputData(organiserName, eventName,  location,  eventDate, eventEndDate, eventTime, eventEndTime, eventLabel, eventType,  eventMaxAttendance);
        makeEventInteractor.execute(MakeEventInputData);

    }
    public void executeCancel(String username){
        CancelInputData cancelInputData = new CancelInputData();
        cancelInteractor.execute();
    }

    public Object getMakeEventInteractor() {
        return makeEventInteractor;
    }

    public Object getCancelInteractor() {
        return cancelInteractor;
    }
}
