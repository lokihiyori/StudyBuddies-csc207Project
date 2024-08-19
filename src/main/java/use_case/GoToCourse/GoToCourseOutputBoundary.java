package use_case.GoToCourse;

/**
 * The GoToCourseOutputBoundary interface defines the methods required for handling
 * the output of the course navigation process. Implementations of this interface
 * should provide the logic for preparing views or responses for successful course navigation.
 */
public interface GoToCourseOutputBoundary {

    /**
     * Prepares the view or response to be shown when the course navigation process
     * is successful, using the provided output data. Implementations should define
     * how the success information, including user data, is presented.
     *
     * @param user the data related to the user involved in the course navigation process.
     */
    void prepareSuccessView(GoToCourseOutputData user);

    /**
     * Prepares the view or response to be shown when the course navigation process
     * is successful, without requiring additional output data. Implementations should define
     * how the success information is presented in this case.
     */
    void prepareSuccessView();
}
