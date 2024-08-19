package use_case.CreateCourse;

import entity.Course;
import entity.GroupChat;

/**
 * The CreateCourseDataAccessInterface defines the methods required for interacting
 * with the data storage system when creating and saving course information.
 * Implementations of this interface are responsible for handling the persistence
 * of course data and checking for the existence of a course by its code.
 */
public interface CreateCourseDataAccessInterface {

    /**
     * Saves the given course to the data storage system.
     * Implementations should handle the persistence logic for storing the course information.
     *
     * @param course the Course object to be saved.
     */
    void saveCourse(Course course);

    /**
     * Checks if a course with the specified code already exists in the data storage system.
     *
     * @param code the code of the course to check for existence.
     * @return true if a course with the given code exists, false otherwise.
     */
    boolean existsByCode(String code);
}
