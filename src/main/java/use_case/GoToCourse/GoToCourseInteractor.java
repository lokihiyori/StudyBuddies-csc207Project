package use_case.GoToCourse;

import entity.User;
import interface_adapter.GoToCourse.CoursePresenter;
import use_case.Login.LoginUserDataAccessInterface;

public class GoToCourseInteractor implements GoToCourseInputBoundary{
    final GoToCourseOutputBoundary goToCoursePagePresenter;
    final LoginUserDataAccessInterface userDataAccessObject;

    public GoToCourseInteractor(GoToCourseOutputBoundary goToCourseOutputBoundary,
                                LoginUserDataAccessInterface loginUserDataAccessInterface){
        this.goToCoursePagePresenter = goToCourseOutputBoundary;
        this.userDataAccessObject = loginUserDataAccessInterface;
    }
    public void execute(GoToCourseInputData goToCourseInputData) {
        User user = userDataAccessObject.get(goToCourseInputData.getUsername());
        GoToCourseOutputData outputUser = new GoToCourseOutputData(user.getName());
        goToCoursePagePresenter.prepareSuccessView(outputUser);
    }

    public void execute(){
        goToCoursePagePresenter.prepareSuccessView();}

    public Object getUserDataAccessObject() {
        return userDataAccessObject;
    }

    public Object getPresenter() {
        return goToCoursePagePresenter;
    }
}
