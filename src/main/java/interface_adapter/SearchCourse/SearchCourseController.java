package interface_adapter.SearchCourse;

import use_case.SearchCourse.SearchCourseInputData;
import use_case.SearchCourse.SearchCourseInputBoundary;

/**
 * Controller for handling course search functionality.
 */
public class SearchCourseController {

    private SearchCourseInputBoundary inputBoundary;

    /**
     * Constructs a SearchCourseController with the specified input boundary.
     *
     * @param inputBoundary the input boundary to be used for course search
     */
    public SearchCourseController(SearchCourseInputBoundary inputBoundary) {

        this.inputBoundary = inputBoundary;
    }

    /**
     * Handles the input query for searching courses.
     *
     * @param query the query string to search for courses
     */
    public void handleInput(String query) {

        inputBoundary.searchCourse(new SearchCourseInputData(query));
    }

}
