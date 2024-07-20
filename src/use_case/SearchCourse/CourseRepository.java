package use_case.SearchCourse;

import entity.Course;

public interface CourseRepository {

    void addCourse(Course course);
    Course findCourseByCode(String courseId);
    Course findCourseByName(String courseName);

}
