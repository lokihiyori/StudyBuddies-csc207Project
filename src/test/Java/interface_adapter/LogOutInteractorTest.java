package interface_adapter;

import interface_adapter.logOut.LogOutInteractor;
import interface_adapter.logOut.LogoOutOutputBoundary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class LogOutInteractorTest {

    private LogOutInteractor logOutInteractor;
    private LogoOutOutputBoundary mockOutputBoundary;

    @BeforeEach
    void setUp() {
        mockOutputBoundary = Mockito.mock(LogoOutOutputBoundary.class);
        logOutInteractor = new LogOutInteractor(mockOutputBoundary);
    }

    @Test
    void testExecute() {
        // Act
        logOutInteractor.execute();

        // Assert
        verify(mockOutputBoundary, times(1)).prepareSuccessView();
    }
}

