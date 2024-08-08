package use_case;

import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import use_case.GoToCourse.GoToCourseInputData;
import use_case.GoToCourse.GoToCourseInteractor;
import use_case.GoToCourse.GoToCourseOutputBoundary;
import use_case.GoToCourse.GoToCourseOutputData;
import use_case.Login.LoginUserDataAccessInterface;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GoToCourseInteractorTest {

    private GoToCourseInteractor interactor;
    private GoToCourseOutputBoundary mockOutputBoundary;
    private LoginUserDataAccessInterface mockUserDataAccess;

    @BeforeEach
    void setUp() {
        mockOutputBoundary = mock(GoToCourseOutputBoundary.class);
        mockUserDataAccess = mock(LoginUserDataAccessInterface.class);
        interactor = new GoToCourseInteractor(mockOutputBoundary, mockUserDataAccess);
    }

    @Test
    void testExecuteWithUserSuccess() {
        String username = "testUser";
        User mockUser = mock(User.class);
        when(mockUserDataAccess.get(username)).thenReturn(mockUser);
        when(mockUser.getName()).thenReturn("Test User");

        GoToCourseInputData inputData = new GoToCourseInputData(username);
        interactor.execute(inputData);

        ArgumentCaptor<GoToCourseOutputData> argumentCaptor = ArgumentCaptor.forClass(GoToCourseOutputData.class);
        verify(mockOutputBoundary).prepareSuccessView(argumentCaptor.capture());

        GoToCourseOutputData capturedOutput = argumentCaptor.getValue();
        assertEquals("Test User", capturedOutput.getUsername());
    }

    @Test
    void testExecuteWithoutUserSuccess() {
        interactor.execute();

        verify(mockOutputBoundary).prepareSuccessView();
    }
}

