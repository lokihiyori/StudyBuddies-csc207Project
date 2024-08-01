package interface_adapter.SearchCourse;

import entity.Course;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SearchCourseViewModel {

    private Course course;
    private String message;
    private final PropertyChangeSupport support;
    private String error;

    public SearchCourseViewModel() {
        this.support = new PropertyChangeSupport(this);
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        Course oldCourse = this.course;
        this.course = course;
        support.firePropertyChange("course", oldCourse, course);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        String oldMessage = this.message;
        this.message = message;
        support.firePropertyChange("message", oldMessage, message);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    public void setError(String error) {
        String oldError = this.error;
        this.error = error;
        support.firePropertyChange("error", oldError, error);
    }
}
