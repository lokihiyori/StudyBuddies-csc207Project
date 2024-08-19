package interface_adapter.GoToCourse;

import interface_adapter.logOut.LogOutInputBoundary;
import use_case.CreateEvent.CreateEventInputBoundary;
import use_case.joinEvent.joinEventInputBoundary;
import use_case.CreateEvent.CreateEventInputData;
import use_case.GoToCourse.GoToCourseInputBoundary;
import use_case.MakeEvent.makeEventInputBoundary;
import use_case.MakeEvent.makeEventInputData;
/**
 * Controller responsible for handling user interactions in the course view.
 * It manages interactions with use cases related to course navigation, event creation, and event joining.
 */
public class CourseViewController {
    final GoToCourseInputBoundary goToCourseInputBoundary;
    final CreateEventInputBoundary createEventInteractor;
    final joinEventInputBoundary joinEventInputBoundary;

    /**
     * Constructs a new CourseViewController with the specified input boundaries.
     *
     * @param goToCourseInputBoundary the input boundary for navigating to a course
     * @param joinEventInputBoundary the input boundary for joining events
     * @param createEventInteractor the input boundary for creating events
     */
    public CourseViewController(GoToCourseInputBoundary goToCourseInputBoundary, joinEventInputBoundary joinEventInputBoundary, CreateEventInputBoundary createEventInteractor){
        this.goToCourseInputBoundary = goToCourseInputBoundary;
        this.joinEventInputBoundary = joinEventInputBoundary;
        this.createEventInteractor = createEventInteractor;
    }

    /**
     * Executes the log out process using the provided GoToCourseInputBoundary.
     */
    public void executeLogOut(){
        goToCourseInputBoundary.execute();
    }

    /**
     * Executes the process of creating an event with the specified username.
     *
     * @param username the username of the event creator
     */
    public void executeCreateEvent(String username){
        CreateEventInputData createEventInputData = new CreateEventInputData(username);
        createEventInteractor.execute(createEventInputData);
    }

    /**
     * Gets the GoToCourseInputBoundary associated with this controller.
     *
     * @return the GoToCourseInputBoundary
     */

    public Object getGoToCourseInputBoundary() {
        return goToCourseInputBoundary;
    }

    /**
     * Gets the joinEventInputBoundary associated with this controller.
     *
     * @return the joinEventInputBoundary
     */
    public Object getJoinEventUseCaseInteractor() {
        return joinEventInputBoundary;
    }

    /**
     * Gets the CreateEventInputBoundary associated with this controller.
     *
     * @return the CreateEventInputBoundary
     */
    public Object getCreateEventInteractor() {
        return createEventInteractor;
    }
}
