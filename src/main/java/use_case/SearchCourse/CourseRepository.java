package use_case.SearchCourse;

import entity.Course;

/**
 * Interface for the Course Repository.
 * Provides methods for adding and finding courses by code or name.
 */
public interface CourseRepository {

    /**
     * Adds a course to the repository.
     *
     * @param course the course to be added
     */
    void addCourse(Course course);

    /**
     * Finds a course in the repository by its code.
     *
     * @param courseId the code of the course to find
     * @return the course with the specified code, or null if not found
     */
    Course findCourseByCode(String courseId);

    /**
     * Finds a course in the repository by its name.
     *
     * @param courseName the name of the course to find
     * @return the course with the specified name, or null if not found
     */
    Course findCourseByName(String courseName);

}
