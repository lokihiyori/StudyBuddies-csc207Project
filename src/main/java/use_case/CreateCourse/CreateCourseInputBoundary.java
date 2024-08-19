package use_case.CreateCourse;

/**
 * The CreateCourseInputBoundary interface defines the method required for handling
 * the input data when creating a new course. Implementations of this interface
 * are responsible for processing the provided course input data and executing
 * the necessary operations to create the course.
 */
public interface CreateCourseInputBoundary {

    /**
     * Executes the course creation process using the provided input data.
     * Implementations should handle the logic for validating and processing the
     * course data to create a new course in the system.
     *
     * @param createCourseInputData the data required to create a new course.
     */
    void execute(CreateCourseInputData createCourseInputData);
}
