package usecase.CreateCourse;


public interface CreateCourseOutputBoundary {
    void prepareSuccessView(CreateCourseOutputData createCourseOutputData);

    void prepareFailView(String error);
}
