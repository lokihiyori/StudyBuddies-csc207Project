package use_case.SearchCourse;

import entity.Course;

public class SearchCourseOutputData {
    private Course course;
    private String message;

    public SearchCourseOutputData(Course course, String message) {
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
