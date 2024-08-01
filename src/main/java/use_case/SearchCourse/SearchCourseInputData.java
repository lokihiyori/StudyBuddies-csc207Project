package use_case.SearchCourse;

/**
 * Class representing the input data for searching courses.
 */
public class SearchCourseInputData {
    private String query;

    /**
     * Constructs a SearchCourseInputData with the specified query.
     *
     * @param query the search query for the course
     */
    public SearchCourseInputData(String query) {
        this.query = query;
    }

    /**
     * Gets the search query.
     *
     * @return the search query
     */
    public String getQuery() {
        return query;
    }
}
