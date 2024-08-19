package use_case.MakeEvent;

import use_case.Cancel.CancelOutputData;

/**
 * The makeEventOutputBoundary interface defines the methods for preparing the output views
 * related to the creation of a calendar event. It provides mechanisms to handle both success
 * and failure scenarios during the event creation process.
 */
public interface makeEventOutputBoundary {

    /**
     * Prepares the success view when an event is created successfully.
     */
    void prepareMakeEventSuccessView();

    /**
     * Prepares the failure view when the event creation process fails.
     *
     * @param error a description of the error that occurred during event creation.
     */
    void prepareMakeEventFailView(String error);

}
