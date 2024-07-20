package interface_adapter.SearchCourse;

import use_case.SearchCourse.SearchCourseOutputBoundary;
import use_case.SearchCourse.SearchCourseOutputData;

public class SearchCoursePresenter implements SearchCourseOutputBoundary {

    private SearchCourseViewModel viewModel;

    public SearchCoursePresenter(SearchCourseViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void present(SearchCourseOutputData outputData) {
        viewModel.setCourse(outputData.getCourse());
        viewModel.setMessage(outputData.getMessage());
    }

}
