package use_case;

import org.junit.jupiter.api.Test;
import use_case.joinEvent.joinEventInputData;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JoinEventInputDataTest {

    @Test
    void testJoinEventInputData() {
        // Arrange
        String expectedUsername = "testUser";
        String expectedEventName = "testEvent";

        // Act
        joinEventInputData inputData = new joinEventInputData(expectedEventName, expectedUsername);

        // Assert
        assertEquals(expectedUsername, inputData.getUsername());
        assertEquals(expectedEventName, inputData.getEventName());
    }
}
