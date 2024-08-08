package use_case;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.Cancel.CancelInteractor;
import use_case.Cancel.CancelOutputBoundary;

import static org.junit.jupiter.api.Assertions.assertTrue;

class Cancel_Test {
    private CancelOutputBoundaryMock cancelOutputBoundary;
    private CancelInteractor cancelInteractor;

    @BeforeEach
    public void setUp() {
        cancelOutputBoundary = new CancelOutputBoundaryMock();
        cancelInteractor = new CancelInteractor(cancelOutputBoundary);
    }

    @Test
    public void testExecute() {
        // Act
        cancelInteractor.execute();

        // Assert
        assertTrue(cancelOutputBoundary.prepareSuccessViewCalled, "prepareSuccessView should be called");
    }

    private static class CancelOutputBoundaryMock implements CancelOutputBoundary {
        private boolean prepareSuccessViewCalled = false;

        @Override
        public void prepareSuccessView() {
            prepareSuccessViewCalled = true;
        }
    }
}