package use_case.SearchCourse;

/**
 * Interface for the Search Course Input Boundary.
 * Provides a method for searching courses based on input data.
 */
public interface SearchCourseInputBoundary {

    /**
     * Searches for a course based on the provided input data.
     *
     * @param inputData the input data containing the search query
     */
    void searchCourse(SearchCourseInputData inputData);
}