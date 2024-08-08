package interface_adapter;

import interface_adapter.logged_In.LoggedIncontroller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.GoToCourse.GoToCourseInputBoundary;

import static org.mockito.Mockito.*;

class LoggedIncontrollerTest {

    private LoggedIncontroller controller;
    private GoToCourseInputBoundary mockGoToCourseInteractor;

    @BeforeEach
    void setUp() {
        mockGoToCourseInteractor = mock(GoToCourseInputBoundary.class);
        controller = new LoggedIncontroller(mockGoToCourseInteractor);
    }

    @Test
    void testExecuteGoToCourse() {
        String testUsername = "testUser";

        // Execute the method to test
        controller.executeGoToCourse(testUsername);

        // Verify that the interactor was called with the correct data
        verify(mockGoToCourseInteractor).execute(argThat(
                goToCourseInputData -> goToCourseInputData.getUsername().equals(testUsername)
        ));
    }
}
