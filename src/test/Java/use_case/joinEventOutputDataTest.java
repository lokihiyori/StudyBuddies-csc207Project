package use_case;

import org.junit.jupiter.api.Test;
import use_case.joinEvent.joinEventOutputData;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JoinEventOutputDataTest {

    @Test
    void testJoinEventOutputData() {
        // Arrange
        String expectedUsername = "testUser";

        // Act
        joinEventOutputData outputData = new joinEventOutputData(expectedUsername);

        // Assert
        assertEquals(expectedUsername, outputData.getUsername());
    }
}

