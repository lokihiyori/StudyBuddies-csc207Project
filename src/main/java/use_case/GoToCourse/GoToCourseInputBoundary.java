package use_case.GoToCourse;

/**
 * The GoToCourseInputBoundary interface defines the methods required for handling
 * the process of navigating to a course. Implementations of this interface are responsible
 * for executing the necessary logic when accessing a course, either with or without
 * additional input data.
 */
public interface GoToCourseInputBoundary {

    /**
     * Executes the process of navigating to a course using the provided input data.
     * Implementations should handle the logic for accessing the course based on the given data.
     *
     * @param goToCourseInputData the data required to navigate to a specific course.
     */
    void execute(GoToCourseInputData goToCourseInputData);

    /**
     * Executes the process of navigating to a course without any additional input data.
     * Implementations should handle the logic for accessing the course in this case.
     */
    void execute();
}
