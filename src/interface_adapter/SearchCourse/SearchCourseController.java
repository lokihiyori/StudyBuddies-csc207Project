package interface_adapter.SearchCourse;

import use_case.SearchCourse.SearchCourseInputData;
import use_case.SearchCourse.SearchCourseInputBoundary;

public class SearchCourseController {

    private SearchCourseInputBoundary inputBoundary;

    public SearchCourseController(SearchCourseInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public void handleInput(String query) {
        inputBoundary.searchCourse(new SearchCourseInputData(query));
    }

}
