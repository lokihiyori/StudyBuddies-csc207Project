package app;


import data_access.FileEventDataAccessObject;
import data_access.FileUserDataAccessObject;
import entity.CalendarEventFactory;
import entity.CommonCalendarEventFactory;
import entity.Course;
import interface_adapter.CreateEvent.CreateEventController;
import interface_adapter.CreateEvent.CreateEventViewModel;
import interface_adapter.CreateEvent.createEventPresenter;
import interface_adapter.GoToCourse.CourseViewModel;
import interface_adapter.ViewManagerModel;
import use_case.Cancel.CancelInputBoundary;
import use_case.Cancel.CancelInteractor;
import use_case.Cancel.CancelOutputBoundary;
import use_case.CreateEvent.CreateEventInputBoundary;
import use_case.CreateEvent.CreateEventInteractor;
import use_case.MakeEvent.makeEventInputBoundary;
import use_case.MakeEvent.makeEventOutputBoundary;
import use_case.MakeEvent.makeEventInteractor;
import view.CreateEventView;

import java.awt.*;

public class CreateEventUseCaseFactory {
    private CreateEventUseCaseFactory(){
    }
    public static CreateEventView create(ViewManagerModel viewManagerModel, CreateEventViewModel createEventViewModel,
                                         CourseViewModel courseViewModel, FileEventDataAccessObject fileEventDataAccessObject, FileUserDataAccessObject userDataAccessObject, Window mainwindow){
        CreateEventController createEventController = createMakeEventUseCase(viewManagerModel, createEventViewModel, courseViewModel, fileEventDataAccessObject, userDataAccessObject);
        return new CreateEventView(createEventViewModel, createEventController, mainwindow);


    }

    private static CreateEventController createMakeEventUseCase(ViewManagerModel viewManagerModel, CreateEventViewModel createEventViewModel,
                                                                CourseViewModel courseViewModel, FileEventDataAccessObject fileEventDataAccessObject, FileUserDataAccessObject userDataAccessObject){
        CalendarEventFactory calendarEventFactory = new CommonCalendarEventFactory();
        makeEventOutputBoundary createEventOutputBoundary = new createEventPresenter(viewManagerModel, courseViewModel);
        makeEventInputBoundary makeEventInteractor = new makeEventInteractor(fileEventDataAccessObject, userDataAccessObject, createEventOutputBoundary, calendarEventFactory);
        CancelOutputBoundary cancelOutputBoundary = new createEventPresenter(viewManagerModel, courseViewModel);
        CancelInputBoundary cancelInputBoundary = new CancelInteractor(cancelOutputBoundary);

        return new CreateEventController(makeEventInteractor, cancelInputBoundary);

    }

}
