package interface_adapter.CreateCourse;

import use_case.CreateCourse.CreateCourseOutputBoundary;
import use_case.CreateCourse.CreateCourseOutputData;

public class CreateCoursePresenter implements CreateCourseOutputBoundary {
    private final CreateCourseViewModel createCourseViewModel;

    public CreateCoursePresenter(CreateCourseViewModel createCourseViewModel) {
        this.createCourseViewModel = createCourseViewModel;
    }

    @Override
    public void prepareSuccessView(CreateCourseOutputData outputData) {
        CreateCourseState createCourseState = new CreateCourseState(outputData.getName(), outputData.getCode());
        createCourseViewModel.setState(createCourseState);
        createCourseViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        createCourseViewModel.setError(error);
        createCourseViewModel.firePropertyChanged();
    }
}
