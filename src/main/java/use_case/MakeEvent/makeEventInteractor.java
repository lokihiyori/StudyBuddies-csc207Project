package use_case.MakeEvent;

import entity.CalendarEvent;
import entity.CalendarEventFactory;
import entity.User;
import use_case.Login.LoginUserDataAccessInterface;

public class makeEventInteractor implements makeEventInputBoundary{
    final makeEventDataAccessInterface eventDataAccessObject ;
    final makeEventUserDataAccessInterface userDataAccessObject;

    final makeEventOutputBoundary createEventPresenter;
    final CalendarEventFactory calendarEventFactory;

    public makeEventInteractor(makeEventDataAccessInterface eventDataAccessObject, makeEventUserDataAccessInterface userDataAccessObject,
                               makeEventOutputBoundary createEventPresenter,CalendarEventFactory calendarEventFactory) {
        this.eventDataAccessObject = eventDataAccessObject;
        this.userDataAccessObject = userDataAccessObject;
        this.createEventPresenter = createEventPresenter;
        this.calendarEventFactory = calendarEventFactory;
    }

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

    }

