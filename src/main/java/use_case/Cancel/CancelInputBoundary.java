package use_case.Cancel;

/**
 * The CancelInputBoundary interface provides a method for executing a cancellation process.
 * Implementations of this interface are expected to define the specific cancellation logic.
 */
public interface CancelInputBoundary {

    /**
     * Executes the cancellation process.
     * Implementing classes should provide the specific logic for handling a cancellation request.
     */
    void execute();
}
