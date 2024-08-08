package use_case;

import org.junit.jupiter.api.Test;
import use_case.LoginSignup.LoginSignupInputData;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoginSignupInputDataTest {

    @Test
    void testGetUsername() {
        // Arrange
        String expectedUsername = "testUser";
        LoginSignupInputData inputData = new LoginSignupInputData(expectedUsername);

        // Act
        String actualUsername = inputData.getUsername();

        // Assert
        assertEquals(expectedUsername, actualUsername);
    }
}
