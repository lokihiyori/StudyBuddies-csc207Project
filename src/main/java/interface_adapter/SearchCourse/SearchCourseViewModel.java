package interface_adapter.SearchCourse;

import entity.Course;

/**
 * ViewModel for storing and presenting course search results.
 */
public class SearchCourseViewModel {

    private Course course;
    private String message;

    /**
     * Gets the course search result.
     *
     * @return the course search result
     */
    public Course getCourse() {

        return course;
    }

    /**
     * Sets the course search result.
     *
     * @param course the course to be set as the search result
     */
    public void setCourse(Course course) {

        this.course = course;
    }

    /**
     * Gets the message associated with the course search result.
     *
     * @return the message associated with the course search result
     */
    public String getMessage() {

        return message;
    }

    /**
     * Sets the message associated with the course search result.
     *
     * @param message the message to be set
     */
    public void setMessage(String message) {

        this.message = message;
    }


}
