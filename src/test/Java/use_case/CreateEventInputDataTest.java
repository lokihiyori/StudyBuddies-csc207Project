package use_case;

import org.junit.jupiter.api.Test;
import use_case.CreateEvent.CreateEventInputData;

import static org.junit.jupiter.api.Assertions.*;

class CreateEventInputDataTest {

    @Test
    void testCreateEventInputData() {
        String username = "testUser";
        CreateEventInputData inputData = new CreateEventInputData(username);

        assertNotNull(inputData);
        assertEquals(username, inputData.getUsername());
    }
}

