package use_case;

import org.junit.jupiter.api.Test;
import use_case.Cancel.CancelOutputData;

import static org.junit.jupiter.api.Assertions.*;

class CancelOutputDataTest {

    @Test
    void testCancelOutputDataCreation() {
        CancelOutputData cancelOutputData = new CancelOutputData();
        assertNotNull(cancelOutputData);
    }
}
