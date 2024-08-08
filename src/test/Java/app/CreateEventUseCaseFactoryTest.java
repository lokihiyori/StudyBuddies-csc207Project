package app;

import data_access.FileEventDataAccessObject;
import data_access.FileUserDataAccessObject;
import entity.CalendarEventFactory;
import entity.CommonCalendarEventFactory;
import interface_adapter.CreateEvent.CreateEventController;
import interface_adapter.CreateEvent.CreateEventViewModel;
import interface_adapter.CreateEvent.createEventPresenter;
import interface_adapter.GoToCourse.CourseViewModel;
import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_case.Cancel.CancelInteractor;
import use_case.Cancel.CancelOutputBoundary;
import use_case.CreateEvent.CreateEventInputBoundary;
import use_case.CreateEvent.CreateEventInteractor;
import use_case.MakeEvent.makeEventInputBoundary;
import use_case.MakeEvent.makeEventInteractor;
import use_case.MakeEvent.makeEventOutputBoundary;
import view.CreateEventView;

import java.awt.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateEventUseCaseFactoryTest {

    private ViewManagerModel viewManagerModel;
    private CreateEventViewModel createEventViewModel;
    private CourseViewModel courseViewModel;
    private FileEventDataAccessObject fileEventDataAccessObject;
    private FileUserDataAccessObject userDataAccessObject;
    private Window mainWindow;

    @BeforeEach
    void setUp() {
        viewManagerModel = mock(ViewManagerModel.class);
        createEventViewModel = mock(CreateEventViewModel.class);
        courseViewModel = mock(CourseViewModel.class);
        fileEventDataAccessObject = mock(FileEventDataAccessObject.class);
        userDataAccessObject = mock(FileUserDataAccessObject.class);
        mainWindow = mock(Window.class);
    }

    @Test
    void testCreateEventViewCreation() {
        CreateEventView createEventView = CreateEventUseCaseFactory.create(viewManagerModel, createEventViewModel,
                courseViewModel, fileEventDataAccessObject, userDataAccessObject, mainWindow);

        assertNotNull(createEventView);
        assertNotNull(createEventView.getController());
        assertTrue(createEventView.getController() instanceof CreateEventController);

        CreateEventController controller = createEventView.getController();
        assertNotNull(controller.getMakeEventInteractor());
        assertNotNull(controller.getCancelInteractor());

        assertTrue(controller.getMakeEventInteractor() instanceof makeEventInteractor);
        assertTrue(controller.getCancelInteractor() instanceof CancelInteractor);

        makeEventInteractor makeEventInteractor = (makeEventInteractor) controller.getMakeEventInteractor();
        assertNotNull(makeEventInteractor.getFileEventDataAccessObject());
        assertNotNull(makeEventInteractor.getUserDataAccessObject());
        assertNotNull(makeEventInteractor.getCalendarEventFactory());
        assertNotNull(makeEventInteractor.getOutputBoundary());

        assertTrue(makeEventInteractor.getCalendarEventFactory() instanceof CommonCalendarEventFactory);
        assertTrue(makeEventInteractor.getOutputBoundary() instanceof createEventPresenter);

        CancelInteractor cancelInteractor = (CancelInteractor) controller.getCancelInteractor();
        assertNotNull(cancelInteractor.getOutputBoundary());

        assertTrue(cancelInteractor.getOutputBoundary() instanceof createEventPresenter);
    }
}
