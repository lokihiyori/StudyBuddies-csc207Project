package interface_adapter.CreateEvent;
import use_case.Cancel.CancelInputBoundary;
import use_case.Cancel.CancelInputData;
import use_case.MakeEvent.makeEventInputBoundary;
import use_case.MakeEvent.makeEventInputData;

import java.time.LocalDate;
import java.time.LocalTime;

public class CreateEventController {
    final makeEventInputBoundary makeEventucInteractor;
    final CancelInputBoundary cancelInteractor;

    public CreateEventController(makeEventInputBoundary makeEventucInteractor, CancelInputBoundary cancelInteractor){
        this.makeEventucInteractor = makeEventucInteractor;

        this.cancelInteractor = cancelInteractor;
    }
    public void executeMakeEvent(String organiserName, String eventName, String location, LocalDate eventDate, LocalDate eventEndDate, LocalTime eventTime, LocalTime eventEndTime, String eventLabel, String eventLevel, int eventMaxAttendance){
        makeEventInputData MakeEventInputData = new makeEventInputData(organiserName, eventName,  location,  eventDate, eventEndDate, eventTime, eventEndTime, eventLabel,  eventLevel,  eventMaxAttendance);
        makeEventucInteractor.execute(MakeEventInputData);

    }
    public void executeCancel(String username){
        CancelInputData cancelInputData = new CancelInputData();
        cancelInteractor.execute();
    }
}
