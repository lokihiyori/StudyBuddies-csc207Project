package use_case.Cancel;

public class CancelInteractor implements CancelInputBoundary{
    final CancelOutputBoundary cancelOutputBoundary;

    public CancelInteractor(CancelOutputBoundary cancelOutputBoundary){
        this.cancelOutputBoundary = cancelOutputBoundary;
    }

    public void execute(){
        cancelOutputBoundary.prepareSuccessView();
    }

    public Object getOutputBoundary() {
        return cancelOutputBoundary;
    }
}
