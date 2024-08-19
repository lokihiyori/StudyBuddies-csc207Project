package interface_adapter.CreateCourse;

import use_case.CreateCourse.CreateCourseOutputBoundary;
import use_case.CreateCourse.CreateCourseOutputData;

/**
 * Presenter class responsible for preparing the view for course creation results.
 * It implements the {@link CreateCourseOutputBoundary} interface to handle the output data
 * and communicate the results to the view model.
 */
public class CreateCoursePresenter implements CreateCourseOutputBoundary {
    private final CreateCourseViewModel createCourseViewModel;
    /**
     * Constructs a new CreateCoursePresenter with the specified view model.
     *
     * @param createCourseViewModel the view model to update with course creation results
     */
    public CreateCoursePresenter(CreateCourseViewModel createCourseViewModel) {
        this.createCourseViewModel = createCourseViewModel;
    }

    /**
     * Prepares the view for a successful course creation result.
     * Creates a new {@link CreateCourseState} with the course name and code from
     * the output data, sets this state in the view model, and notifies the view model
     * of the change.
     *
     * @param outputData the output data containing course details
     */
    @Override
    public void prepareSuccessView(CreateCourseOutputData outputData) {
        CreateCourseState createCourseState = new CreateCourseState(outputData.getName(), outputData.getCode());
        createCourseViewModel.setState(createCourseState);
        createCourseViewModel.firePropertyChanged();
    }

    /**
     * Prepares the view for a failed course creation result by printing an error message.
     *
     * @param error the error message to be displayed
     */
    @Override
    public void prepareFailView(String error) {
        System.out.println("Error: " + error);
    }
}
