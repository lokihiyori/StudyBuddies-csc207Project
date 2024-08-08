package interface_adapter;

import interface_adapter.login.LoginController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.Cancel.CancelInputBoundary;
import use_case.Login.LoginInputBoundary;
import use_case.Login.LoginInputData;

import static org.mockito.Mockito.*;

class LoginControllerTest {

    private LoginController loginController;
    private LoginInputBoundary mockLoginInputBoundary;
    private CancelInputBoundary mockCancelInputBoundary;

    @BeforeEach
    void setUp() {
        mockLoginInputBoundary = mock(LoginInputBoundary.class);
        mockCancelInputBoundary = mock(CancelInputBoundary.class);
        loginController = new LoginController(mockLoginInputBoundary, mockCancelInputBoundary);
    }

    @Test
    void testExecute() {
        // Arrange
        String username = "testUser";
        String password = "testPass";
        LoginInputData loginInputData = new LoginInputData(username, password);

        // Act
        loginController.execute(username, password);

        // Assert
        verify(mockLoginInputBoundary).execute(refEq(loginInputData));
    }

    @Test
    void testExecuteCancel() {
        // Act
        loginController.executeCancel();

        // Assert
        verify(mockCancelInputBoundary).execute();
    }
}
