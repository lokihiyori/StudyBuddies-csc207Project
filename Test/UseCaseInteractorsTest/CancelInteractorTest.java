package UseCaseInteractorsTest;

import org.junit.Before;
import org.junit.Test;
import use_case.Cancel.CancelOutputBoundary;
import use_case.Cancel.CancelInteractor;

public class CancelInteractorTest {
    private CancelOutputBoundary cancelOutputBoundary;
    private CancelInteractor cancelInteractor;

    @Before
    public void setUp() {
        cancelInteractor = new CancelInteractor(cancelOutputBoundary);
    }

    @Test
    public void test() {
        cancelInteractor.execute();

    }
}
