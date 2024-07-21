package use_case.CreateCourse;

import entity.Course;

public interface CreateCourseDataAccessInterface {
    void saveCourse(Course course);

    boolean existsByCode(String code);
}
