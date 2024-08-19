package use_case.GoToCourse;

import entity.User;
import interface_adapter.GoToCourse.CoursePresenter;
import use_case.Login.LoginUserDataAccessInterface;

/**
 * The GoToCourseInteractor class implements the GoToCourseInputBoundary interface
 * and handles the business logic for navigating to a course. It interacts with the
 * user data access object to retrieve user information and communicates with the
 * output boundary to present the result of the navigation process.
 */
public class GoToCourseInteractor implements GoToCourseInputBoundary{
    final GoToCourseOutputBoundary goToCoursePagePresenter;
    final LoginUserDataAccessInterface userDataAccessObject;

    /**
     * Constructs a new GoToCourseInteractor with the specified output boundary
     * and user data access object.
     *
     * @param goToCourseOutputBoundary the output boundary to handle the result of the course navigation process.
     * @param loginUserDataAccessInterface the data access object to retrieve user information.
     */
    public GoToCourseInteractor(GoToCourseOutputBoundary goToCourseOutputBoundary,
                                LoginUserDataAccessInterface loginUserDataAccessInterface){
        this.goToCoursePagePresenter = goToCourseOutputBoundary;
        this.userDataAccessObject = loginUserDataAccessInterface;
    }

    /**
     * Executes the course navigation process using the provided input data.
     * The process involves retrieving the user information associated with the
     * provided username and preparing the success view through the output boundary.
     *
     * @param goToCourseInputData the data required to navigate to a course, including the username.
     */
    public void execute(GoToCourseInputData goToCourseInputData) {
        User user = userDataAccessObject.get(goToCourseInputData.getUsername());
        GoToCourseOutputData outputUser = new GoToCourseOutputData(user.getName());
        goToCoursePagePresenter.prepareSuccessView(outputUser);
    }

    /**
     * Executes the course navigation process without any additional input data.
     * This method is used when no specific user information is required for navigation.
     */
    public void execute(){
        goToCoursePagePresenter.prepareSuccessView();}

    /**
     * Returns the user data access object associated with this interactor.
     *
     * @return the LoginUserDataAccessInterface instance used by this interactor.
     */
    public Object getUserDataAccessObject() {
        return userDataAccessObject;
    }

    /**
     * Returns the output boundary associated with this interactor.
     *
     * @return the GoToCourseOutputBoundary instance used by this interactor.
     */
    public Object getPresenter() {
        return goToCoursePagePresenter;
    }
}
