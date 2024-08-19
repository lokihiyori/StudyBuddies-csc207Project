package interface_adapter.logged_In;

import use_case.GoToCourse.GoToCourseInputBoundary;
import use_case.GoToCourse.GoToCourseInputData;
import use_case.GoToCourse.GoToCourseInteractor;
/**
 * Controller for handling actions in the logged-in state related to navigating to a course.
 * It interacts with the use case for going to a course.
 */
public class LoggedIncontroller {
    final GoToCourseInputBoundary goToCourseInteractor;

    /**
     * Constructs a new LoggedInController with the specified GoToCourseInputBoundary.
     *
     * @param goToCourseInputBoundary the input boundary for going to a course
     */
    public LoggedIncontroller(GoToCourseInputBoundary goToCourseInputBoundary){
        this.goToCourseInteractor = goToCourseInputBoundary;
    }
    /**
     * Executes the process to navigate to a course for the specified user.
     *
     * @param username the username of the user navigating to the course
     */
    public void executeGoToCourse(String username){
        GoToCourseInputData goToCourseInputData = new GoToCourseInputData(username);
        goToCourseInteractor.execute(goToCourseInputData);
    }

    /**
     * Gets the GoToCourseInputBoundary used by this controller.
     *
     * @return the GoToCourseInputBoundary
     */
    public Object getGoToCourseInputBoundary() {
        return goToCourseInteractor;
    }
}
