package use_case;

import entity.CalendarEvent;
import entity.CommonCalendarEvent;
import entity.CalendarEventFactory;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import use_case.Login.LoginUserDataAccessInterface;
import use_case.MakeEvent.*;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class makeEventInteractorTest {

    private makeEventDataAccessInterface eventDataAccessObject;
    private makeEventUserDataAccessInterface userDataAccessObject;
    private makeEventOutputBoundary createEventPresenter;
    private CalendarEventFactory calendarEventFactory;
    private makeEventInteractor interactor;

    @BeforeEach
    void setUp() {
        eventDataAccessObject = mock(makeEventDataAccessInterface.class);
        userDataAccessObject = mock(makeEventUserDataAccessInterface.class);
        createEventPresenter = mock(makeEventOutputBoundary.class);
        calendarEventFactory = mock(CalendarEventFactory.class);

        interactor = new makeEventInteractor(eventDataAccessObject, userDataAccessObject, createEventPresenter, calendarEventFactory);
    }

    @Test
    void testExecute_Success() {
        // Arrange
        makeEventInputData inputData = new makeEventInputData(
                "John Doe",
                "Tech Conference",
                "Auditorium A",
                LocalDate.of(2023, 8, 15),
                LocalDate.of(2023, 8, 16),
                LocalTime.of(10, 0),
                LocalTime.of(16, 0),
                "Technology",
                "Conference",
                100
        );

        CalendarEvent event = new CommonCalendarEvent(
                "John Doe",
                "Tech Conference",
                LocalDate.of(2023, 8, 15),
                LocalDate.of(2023, 8, 16),
                LocalTime.of(10, 0),
                LocalTime.of(16, 0),
                "Auditorium A",
                100,
                "Conference"
        );

        when(eventDataAccessObject.existsByName("Tech Conference")).thenReturn(false);
        when(calendarEventFactory.create(
                "Tech Conference",
                LocalDate.of(2023, 8, 15),
                LocalDate.of(2023, 8, 16),
                LocalTime.of(10, 0),
                LocalTime.of(16, 0),
                "John Doe",
                100,
                "Conference",
                "Auditorium A")).thenReturn(event);

        // Act
        interactor.execute(inputData);

        // Assert
        verify(eventDataAccessObject).save(event);
        verify(eventDataAccessObject).addParticipant("Tech Conference", "John Doe");
        verify(userDataAccessObject).addEvent("Tech Conference", "John Doe");
        verify(createEventPresenter).prepareMakeEventSuccessView();
    }

    @Test
    void testExecute_EventAlreadyExists() {
        // Arrange
        makeEventInputData inputData = new makeEventInputData(
                "John Doe",
                "Tech Conference",
                "Auditorium A",
                LocalDate.of(2023, 8, 15),
                LocalDate.of(2023, 8, 16),
                LocalTime.of(10, 0),
                LocalTime.of(16, 0),
                "Technology",
                "Conference",
                100
        );

        when(eventDataAccessObject.existsByName("Tech Conference")).thenReturn(true);

        // Act
        interactor.execute(inputData);

        // Assert
        verify(createEventPresenter).prepareMakeEventFailView("Event already exists.");
        verify(eventDataAccessObject, never()).save(any(CalendarEvent.class));
        verify(eventDataAccessObject, never()).addParticipant(anyString(), anyString());
        verify(userDataAccessObject, never()).addEvent(anyString(), anyString());
    }
}
