package usecase.SearchCourse;

import entity.Course;

public class CourseSearchOutputData {
    private Course course;
    private String message;

    public CourseSearchOutputData(Course course, String message) {
        this.course = course;
        this.message = message;
    }

    public Course getCourse() {
        return course;
    }

    public String getMessage() {
        return message;
    }
}
