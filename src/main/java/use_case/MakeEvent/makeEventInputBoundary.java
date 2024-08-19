package use_case.MakeEvent;

/**
 * The makeEventInputBoundary interface defines the method required for handling
 * the input data related to creating or managing calendar events. Implementations
 * of this interface are responsible for processing the input data and executing
 * the necessary operations to create or manage events.
 */
public interface makeEventInputBoundary {

    /**
     * Executes the operation for creating or managing a calendar event using the
     * provided input data. Implementations should handle the logic for processing
     * the event details and performing the appropriate actions.
     *
     * @param makeEventInputData the data required to create or manage a calendar event.
     */
    void execute(makeEventInputData makeEventInputData);
}
