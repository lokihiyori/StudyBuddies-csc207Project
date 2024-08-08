package use_case;

import entity.CommonUser;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import use_case.Login.*;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class LoginInteractorTest {

    private LoginUserDataAccessInterface userDataAccessObject;
    private LoginOutputBoundary loginPresenter;
    private LoginInteractor loginInteractor;

    @BeforeEach
    void setUp() {
        userDataAccessObject = mock(LoginUserDataAccessInterface.class);
        loginPresenter = mock(LoginOutputBoundary.class);
        loginInteractor = new LoginInteractor(userDataAccessObject, loginPresenter);
    }

    @Test
    void testExecute_UserDoesNotExist() {
        // Arrange
        when(userDataAccessObject.existsByName(anyString())).thenReturn(false);
        LoginInputData inputData = new LoginInputData("testUser", "testPassword");

        // Act
        loginInteractor.execute(inputData);

        // Assert
        verify(loginPresenter).prepareFailView("testUser: Account does not exist.");
    }

    @Test
    void testExecute_IncorrectPassword() {
        // Arrange
        CommonUser mockUser = new CommonUser("testUser", "test@example.com", "correctPassword", LocalDateTime.now());
        when(userDataAccessObject.existsByName(anyString())).thenReturn(true);
        when(userDataAccessObject.get(anyString())).thenReturn(mockUser);
        LoginInputData inputData = new LoginInputData("testUser", "wrongPassword");

        // Act
        loginInteractor.execute(inputData);

        // Assert
        verify(loginPresenter).prepareFailView("Incorrect password for testUser.");
    }

    @Test
    void testExecute_SuccessfulLogin() {
        CommonUser mockUser = new CommonUser("testUser", "test@example.com", "testPassword", LocalDateTime.now());
        when(userDataAccessObject.existsByName(anyString())).thenReturn(true);
        when(userDataAccessObject.get(anyString())).thenReturn(mockUser);
        LoginInputData inputData = new LoginInputData("testUser", "testPassword");

        // Act
        loginInteractor.execute(inputData);

        // Assert
        ArgumentCaptor<LoginOutputData> captor = ArgumentCaptor.forClass(LoginOutputData.class);
        verify(loginPresenter).prepareSuccessView(captor.capture());
        LoginOutputData capturedOutputData = captor.getValue();
        assertEquals("testUser", capturedOutputData.getUsername());
    }
}
