package interface_adapter.EnrollInCourse;



import interface_adapter.SearchCourse.SearchCourseViewModel;
import interface_adapter.ViewModel;
import use_case.EnrollInCourse.EnrollInCourseOutputBoundary;

public class EnrollInCoursePresenter implements EnrollInCourseOutputBoundary {
    private final SearchCourseViewModel viewModel;

    public EnrollInCoursePresenter(SearchCourseViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void presentEnrollmentResult(boolean success, String courseCode) {
        if (success) {
            viewModel.setMessage("Successfully enrolled in course: " + courseCode);
        } else {
            viewModel.setMessage("Failed to enroll in course: " + courseCode);
        }
    }
}
