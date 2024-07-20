package interface_adapter.CreateCourse;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CreateCourseViewModel {
    private CreateCourseState state = new CreateCourseState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private String error;
    public CreateCourseViewModel() {super();}
    public void setState(CreateCourseState createCourseState) {
        this.state = createCourseState;
    }

    public CreateCourseState getState() {
        return state;
    }

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
        if (error != null) {
            support.firePropertyChange("error", null, this.error);
        }
    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
