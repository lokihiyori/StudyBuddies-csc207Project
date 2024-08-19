package use_case.joinEvent;

/**
 * The JoinEventInputBoundary interface defines the method required for handling
 * the process of a user joining an event. Implementations of this interface are
 * responsible for processing the input data related to joining an event and
 * executing the necessary operations.
 */
public interface joinEventInputBoundary {

    /**
     * Executes the process of a user joining an event using the provided input data.
     * Implementations should handle the logic for validating and processing the
     * event participation request.
     *
     * @param joinEventInputData the data required for a user to join an event.
     */
    void execute(joinEventInputData joinEventInputData);

}
