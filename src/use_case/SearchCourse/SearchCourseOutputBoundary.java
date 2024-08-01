package use_case.SearchCourse;

public interface SearchCourseOutputBoundary {
    void present(SearchCourseOutputData outputData);
    void showError(String message);
}
