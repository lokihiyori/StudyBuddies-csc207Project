package interface_adapter.EnrollInCourse;

import use_case.EnrollInCourse.EnrollInCourseInputBoundary;

public class EnrollInCourseController {
    private final EnrollInCourseInputBoundary interactor;

    public EnrollInCourseController(EnrollInCourseInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void enrollInCourse(String courseCode, String userEmail) {
        interactor.enrollInCourse(courseCode, userEmail);
    }
}
