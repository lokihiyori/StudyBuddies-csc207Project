package interface_adapter.CreateCourse;

import usecase.CreateCourse.CreateCourseInputBoundary;
import usecase.CreateCourse.CreateCourseInputData;

public class CreateCourseController {
    private final CreateCourseInputBoundary createCourseInteractor;

    public CreateCourseController(CreateCourseInputBoundary createCourseInteractor) {
        this.createCourseInteractor = createCourseInteractor;
    }

    public void executeCreateCourse(String name, String code) {
        CreateCourseInputData inputData = new CreateCourseInputData(name, code);
        createCourseInteractor.execute(inputData);
    }
}
