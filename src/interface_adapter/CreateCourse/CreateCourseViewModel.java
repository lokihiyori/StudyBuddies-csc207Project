package interface_adapter.CreateCourse;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CreateCourseViewModel {

    private CreateCourseState state = new CreateCourseState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public CreateCourseState getState() {
        return state;
    }

    public void setState(CreateCourseState state) {
        this.state = state;
    }

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
