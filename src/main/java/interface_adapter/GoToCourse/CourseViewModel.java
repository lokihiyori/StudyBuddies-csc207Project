package interface_adapter.GoToCourse;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * ViewModel for managing and interacting with the course view.
 * Handles the state related to course view and supports property change notifications.
 */
public class CourseViewModel extends ViewModel {

    /**
     * The label for the title of the course view.
     */
    public final String TITLE_LABEL = "CourseView";

    /**
     * The label for the log out button in the course view.
     */

    private CourseState state = new CourseState();

    public static final String LOGOUT_BUTTON_LABEL = "Log out";

    private String courseUser;

    /**
     * Constructs a new CourseViewModel with the default view name "CourseView".
     */
    public CourseViewModel() {
        super("CourseView");
    }

    /**
     * Sets the current state of the course view model.
     *
     * @param state the new state to be set
     */
    public void setState(CourseState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Login Presenter will call to let the ViewModel know
    // to alert the View

    /**
     * Fires a property change event to notify listeners of changes to the state.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }


    /**
     * Adds a property change listener to be notified of changes to the state.
     *
     * @param listener the listener to be added
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Gets the current state of the course view model.
     *
     * @return the current state
     */
    public CourseState getState() {
        return state;
    }

}
