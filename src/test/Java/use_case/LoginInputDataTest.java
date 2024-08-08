package use_case;

import org.junit.jupiter.api.Test;
import use_case.Login.LoginInputData;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoginInputDataTest {

    @Test
    void testGetUsername() {
        // Arrange
        String username = "testUser";
        String password = "testPassword";
        LoginInputData loginInputData = new LoginInputData(username, password);

        // Act
        String result = loginInputData.getUsername();

        // Assert
        assertEquals(username, result);
    }

    @Test
    void testGetPassword() {
        // Arrange
        String username = "testUser";
        String password = "testPassword";
        LoginInputData loginInputData = new LoginInputData(username, password);

        // Act
        String result = loginInputData.getPassword();

        // Assert
        assertEquals(password, result);
    }
}
