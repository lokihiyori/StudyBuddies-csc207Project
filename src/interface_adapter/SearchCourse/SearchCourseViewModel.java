package interface_adapter.SearchCourse;

import entity.Course;

public class SearchCourseViewModel {

    private Course course;
    private String message;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
