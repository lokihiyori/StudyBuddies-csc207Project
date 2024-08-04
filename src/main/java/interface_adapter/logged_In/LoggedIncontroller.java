package interface_adapter.logged_In;

import use_case.GoToCourse.GoToCourseInputBoundary;
import use_case.GoToCourse.GoToCourseInputData;
import use_case.GoToCourse.GoToCourseInteractor;

public class LoggedIncontroller {
    final GoToCourseInputBoundary goToCourseInteractor;

    public LoggedIncontroller(GoToCourseInputBoundary goToCourseInputBoundary){
        this.goToCourseInteractor = goToCourseInputBoundary;
    }

    public void executeGoToCourse(String username){
        GoToCourseInputData goToCourseInputData = new GoToCourseInputData(username);
        goToCourseInteractor.execute(goToCourseInputData);
    }
}
