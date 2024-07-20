package data_access;

import entity.Course;

import use_case.SearchCourse.CourseRepository;

import java.util.ArrayList;
import java.util.List;

public class CourseListDAO implements CourseRepository {

    private List<Course> courses;

    public CourseListDAO() {
        this.courses = new ArrayList<>();
    }

    @Override
    public void addCourse(Course course) {
        courses.add(course);
    }

    @Override
    public Course findCourseByCode(String courseCode) {
        for (Course course : courses) {
            if (course.getCode().equals(courseCode.toUpperCase())) {
                return course;
            }
        }
        return null;
    }

    @Override
    public Course findCourseByName(String courseName) {
        for (Course course : courses) {
            if (course.getName().equalsIgnoreCase(courseName.toUpperCase())) {
                return course;
            }
        }
        return null;
    }
}
