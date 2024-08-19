package use_case.Cancel;

/**
 * The CancelOutputBoundary interface defines a method for preparing the view
 * after a successful cancellation process. Implementing classes should provide
 * the specific logic to display or handle the success view.
 */
public interface CancelOutputBoundary {

    /**
     * Prepares the view or response to be shown after the cancellation has been successfully executed.
     * Implementing classes should define how the success view is constructed and presented.
     */
    void prepareSuccessView();

}