package use_case.CreateCourse;

/**
 * The CreateCourseOutputBoundary interface defines the methods required for
 * handling the output of the course creation process. Implementations of this
 * interface should provide the logic for preparing views or responses for both
 * successful and failed course creation attempts.
 */
public interface CreateCourseOutputBoundary {

    /**
     * Prepares the view or response to be shown when the course creation process
     * is successful. Implementations should define how the success information
     * is presented using the provided output data.
     *
     * @param createCourseOutputData the data to be used for preparing the success view.
     */
    void prepareSuccessView(CreateCourseOutputData createCourseOutputData);

    /**
     * Prepares the view or response to be shown when the course creation process
     * fails. Implementations should define how the error message is presented.
     *
     * @param error the error message explaining why the course creation failed.
     */
    void prepareFailView(String error);
}
