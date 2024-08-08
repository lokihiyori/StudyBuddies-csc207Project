package use_case.Cancel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

class CancelInteractorTest {

    private CancelOutputBoundary mockOutputBoundary;
    private CancelInteractor interactor;

    @BeforeEach
    void setUp() {
        mockOutputBoundary = Mockito.mock(CancelOutputBoundary.class);
        interactor = new CancelInteractor(mockOutputBoundary);
    }

    @Test
    void testExecute() {
        interactor.execute();
        verify(mockOutputBoundary).prepareSuccessView();
    }
}

