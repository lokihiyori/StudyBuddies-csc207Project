package use_case;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import use_case.joinEvent.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class JoinEventInteractorTest {

    private joinEventDataAccessInterface mockDataAccess;
    private joinEventOutputBoundary mockPresenter;
    private joinEventInteractor interactor;

    @BeforeEach
    void setUp() {
        mockDataAccess = Mockito.mock(joinEventDataAccessInterface.class);
        mockPresenter = Mockito.mock(joinEventOutputBoundary.class);
        interactor = new joinEventInteractor(mockDataAccess, mockPresenter);
    }

    @Test
    void testExecute() {
        // Arrange
        String eventName = "testEvent";
        String username = "testUser";
        joinEventInputData inputData = new joinEventInputData(eventName, username);

        // Act
        interactor.execute(inputData);

        // Assert
        verify(mockDataAccess, times(1)).addParticipant(eventName, username);

        ArgumentCaptor<joinEventOutputData> outputDataCaptor = ArgumentCaptor.forClass(joinEventOutputData.class);
        verify(mockPresenter, times(1)).prepareJoinEventSuccessView(outputDataCaptor.capture());
        joinEventOutputData capturedOutputData = outputDataCaptor.getValue();

        assertEquals(username, capturedOutputData.getUsername());
    }
}
