package use_case.Cancel;

/**
 * The CancelInteractor class implements the CancelInputBoundary interface
 * and is responsible for handling the cancellation process.
 * It interacts with the output boundary to prepare the success view after executing the cancellation.
 */
public class CancelInteractor implements CancelInputBoundary{
    final CancelOutputBoundary cancelOutputBoundary;

    /**
     * Constructs a new CancelInteractor with the specified output boundary.
     *
     * @param cancelOutputBoundary the output boundary that will handle the response after the cancellation process is executed.
     */
    public CancelInteractor(CancelOutputBoundary cancelOutputBoundary){
        this.cancelOutputBoundary = cancelOutputBoundary;
    }

    /**
     * Executes the cancellation process by interacting with the output boundary
     * to prepare the success view.
     */
    @Override
    public void execute(){
        cancelOutputBoundary.prepareSuccessView();
    }

    /**
     * Returns the output boundary associated with this interactor.
     *
     * @return the CancelOutputBoundary instance used by this interactor.
     */
    public Object getOutputBoundary() {
        return cancelOutputBoundary;
    }
}
