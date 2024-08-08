package use_case;

import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import use_case.CreateEvent.CreateEventInputData;
import use_case.CreateEvent.CreateEventInteractor;
import use_case.CreateEvent.CreateEventOutputBoundary;
import use_case.CreateEvent.CreateEventOutputData;
import use_case.Login.LoginUserDataAccessInterface;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CreateEventInteractorTest {

    private CreateEventInteractor interactor;
    private CreateEventOutputBoundary mockOutputBoundary;
    private LoginUserDataAccessInterface mockUserDataAccess;

    @BeforeEach
    void setUp() {
        mockOutputBoundary = mock(CreateEventOutputBoundary.class);
        mockUserDataAccess = mock(LoginUserDataAccessInterface.class);
        interactor = new CreateEventInteractor(mockOutputBoundary, mockUserDataAccess);
    }

    @Test
    void testExecuteSuccess() {
        String username = "testUser";
        User mockUser = mock(User.class);
        when(mockUserDataAccess.get(username)).thenReturn(mockUser);
        when(mockUser.getName()).thenReturn("Test User");

        CreateEventInputData inputData = new CreateEventInputData(username);
        interactor.execute(inputData);

        ArgumentCaptor<CreateEventOutputData> argumentCaptor = ArgumentCaptor.forClass(CreateEventOutputData.class);
        verify(mockOutputBoundary).prepareSuccessView(argumentCaptor.capture());

        CreateEventOutputData capturedOutput = argumentCaptor.getValue();
        assertEquals("Test User", capturedOutput.getUsername());
    }
}
