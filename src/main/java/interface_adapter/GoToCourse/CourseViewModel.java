package interface_adapter.GoToCourse;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CourseViewModel extends ViewModel {
    public final String TITLE_LABEL = "CourseView";

    private CourseState state = new CourseState();

    public static final String LOGOUT_BUTTON_LABEL = "Log out";

    private String courseUser;

    public CourseViewModel() {
        super("CourseView");
    }

    public void setState(CourseState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Login Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }


    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public CourseState getState() {
        return state;
    }


    public String getCourseUser() {
        return courseUser;
    }

    public void setCourseUser(String courseUser) {
        this.courseUser = courseUser;
    }
}
