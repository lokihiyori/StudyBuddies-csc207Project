package use_case.CreateEvent;

/**
 * The CreateEventInputBoundary interface defines the method required for handling
 * the input data when creating a new event. Implementations of this interface
 * are responsible for processing the provided event input data and executing
 * the necessary operations to create the event.
 */
public interface CreateEventInputBoundary {

    /**
     * Executes the event creation process using the provided input data.
     * Implementations should handle the logic for validating and processing the
     * event data to create a new event in the system.
     *
     * @param createEventInputData the data required to create a new event.
     */
    void execute(CreateEventInputData createEventInputData);
}
