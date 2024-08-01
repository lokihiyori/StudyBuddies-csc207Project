package use_case.SearchCourse;

import entity.Course;

/**
 * Class representing the output data for a course search.
 */
public class SearchCourseOutputData {
    private Course course;
    private String message;

    /**
     * Constructs a SearchCourseOutputData with the specified course and message.
     *
     * @param course the course that was found
     * @param message the message associated with the search result
     */
    public SearchCourseOutputData(Course course, String message) {
        this.course = course;
        this.message = message;
    }

    /**
     * Gets the course found in the search.
     *
     * @return the course found in the search
     */
    public Course getCourse() {

        return course;
    }

    /**
     * Gets the message associated with the search result.
     *
     * @return the message associated with the search result
     */
    public String getMessage() {

        return message;
    }
}
