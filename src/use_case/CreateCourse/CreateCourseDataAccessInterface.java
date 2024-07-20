package use_case.CreateCourse;

import entity.Course;
import entity.GroupChat;

public interface CreateCourseDataAccessInterface {
    void saveCourse(Course course);

    boolean existsByCode(String code);
}
