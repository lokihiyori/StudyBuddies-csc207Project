package use_case.MakeEvent;

import use_case.Cancel.CancelOutputData;

public interface makeEventOutputBoundary {
    void prepareMakeEventSuccessView();
    void prepareMakeEventFailView(String error);


}
