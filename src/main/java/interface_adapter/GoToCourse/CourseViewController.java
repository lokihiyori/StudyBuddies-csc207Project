package interface_adapter.GoToCourse;

import interface_adapter.logOut.LogOutInputBoundary;
import use_case.GoToCourse.GoToCourseInputBoundary;

public class CourseViewController {
    final GoToCourseInputBoundary goToCourseInputBoundary;


    public CourseViewController(GoToCourseInputBoundary goToCourseInputBoundary){
        this.goToCourseInputBoundary = goToCourseInputBoundary;
    }
    public void excuteLogOut(){
        goToCourseInputBoundary.execute();
    }

}
