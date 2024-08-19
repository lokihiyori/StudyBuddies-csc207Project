package interface_adapter.CreateCourse;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
/**
 * ViewModel class responsible for managing the state of the course creation process.
 * It provides methods to get and set the state, notify listeners of state changes,
 * and manage property change listeners.
 */
public class CreateCourseViewModel {

    private CreateCourseState state = new CreateCourseState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    /**
     * Returns the current state of the course creation process.
     *
     * @return the current {@link CreateCourseState}
     */
    public CreateCourseState getState() {
        return state;
    }

    /**
     * Sets the state of the course creation process.
     *
     * @param state the new {@link CreateCourseState}
     */
    public void setState(CreateCourseState state) {
        this.state = state;
    }

    /**
     * Notifies all registered property change listeners that the state has changed.
     * The listeners are informed that the "state" property has been updated.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Adds a property change listener to be notified of changes to the state.
     *
     * @param listener the {@link PropertyChangeListener} to add
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
