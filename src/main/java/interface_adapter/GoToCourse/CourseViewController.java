package interface_adapter.GoToCourse;

import use_case.CreateEvent.CreateEventInputBoundary;
import use_case.joinEvent.joinEventInputBoundary;
import use_case.CreateEvent.CreateEventInputData;
import use_case.GoToCourse.GoToCourseInputBoundary;

public class CourseViewController {
    final GoToCourseInputBoundary goToCourseInputBoundary;
    final CreateEventInputBoundary createEventInteractor;
    final joinEventInputBoundary joinEventInputBoundary;


    public CourseViewController(GoToCourseInputBoundary goToCourseInputBoundary, joinEventInputBoundary joinEventInputBoundary, CreateEventInputBoundary createEventInteractor){
        this.goToCourseInputBoundary = goToCourseInputBoundary;
        this.joinEventInputBoundary = joinEventInputBoundary;
        this.createEventInteractor = createEventInteractor;
    }
    public void executeLogOut(){
        goToCourseInputBoundary.execute();
    }
    public void executeCreateEvent(String username){
        CreateEventInputData createEventInputData = new CreateEventInputData(username);
        createEventInteractor.execute(createEventInputData);
    }

}
