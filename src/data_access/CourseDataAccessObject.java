package data_access;

import entity.Course;
import usecase.CreateCourse.CreateCourseDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

public class CourseDataAccessObject implements CreateCourseDataAccessInterface {
    private final Map<String, Course> courseMap = new HashMap<>();
    @Override
    public void saveCourse(Course course) {
        courseMap.put(course.getCode(), course);
    }

    public Map<String, Course> getCourseMap() {
        return courseMap;
    }

    @Override
    public boolean existsByCode(String code) {
        return courseMap.containsKey(code);
    }
}
