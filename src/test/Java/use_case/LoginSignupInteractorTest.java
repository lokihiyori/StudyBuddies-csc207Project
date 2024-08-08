package use_case;

import entity.User;
import data_access.FileUserDataAccessObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import use_case.LoginSignup.LoginSignupInputData;
import use_case.LoginSignup.LoginSignupInteractor;
import use_case.LoginSignup.LoginSignupOutputBoundary;
import use_case.LoginSignup.LoginSignupOutputData;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class LoginSignupInteractorTest {

    @Mock
    private LoginSignupOutputBoundary loginSignupOutputBoundary;

    @Mock
    private FileUserDataAccessObject userDataAccessObject;

    @InjectMocks
    private LoginSignupInteractor loginSignupInteractor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testExecute() {
        // Arrange
        String username = "testUser";
        User user = mock(User.class);
        when(user.getName()).thenReturn(username);
        when(userDataAccessObject.get(username)).thenReturn(user);

        LoginSignupInputData inputData = new LoginSignupInputData(username);

        // Act
        loginSignupInteractor.execute(inputData);

        // Assert
        ArgumentCaptor<LoginSignupOutputData> captor = ArgumentCaptor.forClass(LoginSignupOutputData.class);
        verify(loginSignupOutputBoundary).prepareSuccessView(captor.capture());
        LoginSignupOutputData capturedOutputData = captor.getValue();
        assertEquals(username, capturedOutputData.getUsername());
    }
}

