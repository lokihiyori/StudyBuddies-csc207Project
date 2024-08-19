package use_case.GoToCourse;

/**
 * The GoToCourseDataAccessInterface defines the method required for interacting
 * with the data storage system when navigating to a course. Implementations of this
 * interface are responsible for updating the database to reflect any changes or actions
 * taken when accessing a course.
 */
public interface GoToCourseDataAccessInterface {

    /**
     * Updates the database to reflect any necessary changes when a course is accessed.
     * Implementations should handle the logic for updating the relevant data in the storage system.
     */
    void updateDatabase();
}
