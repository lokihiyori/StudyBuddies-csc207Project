package interface_adapter.SearchCourse;

import usecase.SearchCourse.SearchCourseOutputBoundary;
import usecase.SearchCourse.SearchCourseOutputData;

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
