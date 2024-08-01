package interface_adapter.SearchCourse;

import use_case.SearchCourse.SearchCourseOutputBoundary;
import use_case.SearchCourse.SearchCourseOutputData;

/**
 * Presenter for handling the output of course search functionality.
 */
public class SearchCoursePresenter implements SearchCourseOutputBoundary {

    private SearchCourseViewModel viewModel;

    /**
     * Constructs a SearchCoursePresenter with the specified view model.
     *
     * @param viewModel the view model to be used for displaying course search results
     */
    public SearchCoursePresenter(SearchCourseViewModel viewModel) {

        this.viewModel = viewModel;
    }

    /**
     * Presents the course search results by updating the view model.
     *
     * @param outputData the output data containing the course search results
     */
    @Override
    public void present(SearchCourseOutputData outputData) {
        viewModel.setCourse(outputData.getCourse());
        viewModel.setMessage(outputData.getMessage());
    }

}
