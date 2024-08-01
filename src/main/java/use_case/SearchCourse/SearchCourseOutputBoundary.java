package use_case.SearchCourse;

/**
 * Interface for the Search Course Output Boundary.
 * Provides a method for presenting course search results.
 */
public interface SearchCourseOutputBoundary {

    /**
     * Presents the course search results.
     *
     * @param outputData the output data containing the search results
     */
    void present(SearchCourseOutputData outputData) ;
}
