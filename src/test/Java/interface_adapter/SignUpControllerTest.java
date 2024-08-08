package interface_adapter;

import interface_adapter.SignUp.SignUpController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_case.LoginSignup.LoginSignupInputBoundary;
import use_case.LoginSignup.LoginSignupInputData;
import use_case.Signup.SignupInputBoundary;
import use_case.Signup.SignupInputData;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

class SignUpControllerTest {

    private SignUpController signUpController;
    private SignupInputBoundary mockSignupInputBoundary;
    private LoginSignupInputBoundary mockLoginSignupInputBoundary;

    @BeforeEach
    void setUp() {
        mockSignupInputBoundary = Mockito.mock(SignupInputBoundary.class);
        mockLoginSignupInputBoundary = Mockito.mock(LoginSignupInputBoundary.class);
        signUpController = new SignUpController(mockSignupInputBoundary, mockLoginSignupInputBoundary);
    }

    @Test
    void testExecuteSignup() {
        // Arrange
        String username = "testUser";
        String email = "test@example.com";
        String password = "password123";
        String repeatPassword = "password123";

        // Act
        signUpController.execute(username, email, password, repeatPassword);

        // Assert
        verify(mockSignupInputBoundary, times(1)).execute(any(SignupInputData.class));
    }

    @Test
    void testExecuteLogin() {
        // Arrange
        String username = "testUser";

        // Act
        signUpController.executeLogin(username);

        // Assert
        verify(mockLoginSignupInputBoundary, times(1)).execute(any(LoginSignupInputData.class));
    }
}

