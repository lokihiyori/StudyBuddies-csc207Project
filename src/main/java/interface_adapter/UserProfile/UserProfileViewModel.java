package interface_adapter.UserProfile;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

/**
 * ViewModel for user profile management. Holds user profile information and supports property change listeners.
 */
public class UserProfileViewModel {
    private String name;
    private String email;
    private String creationTime;
    private List<String> courseCodes;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Sets the name property and fires a property change event if the value changes.
     *
     * @param name the new name value
     */
    public void setName(String name) {
        String oldName = this.name;
        this.name = name;
        support.firePropertyChange("name", oldName, name);
    }

    /**
     * Sets the email property and fires a property change event if the value changes.
     *
     * @param email the new email value
     */
    public void setEmail(String email) {
        String oldEmail = this.email;
        this.email = email;
        support.firePropertyChange("email", oldEmail, email);
    }

    /**
     * Sets the creation time property and fires a property change event if the value changes.
     *
     * @param creationTime the new creation time value
     */
    public void setCreationTime(String creationTime) {
        String oldCreationTime = this.creationTime;
        this.creationTime = creationTime;
        support.firePropertyChange("creationTime", oldCreationTime, creationTime);
    }

    /**
     * Sets the course codes property and fires a property change event if the value changes.
     *
     * @param courseCodes the new list of course codes
     */
    public void setCourseCodes(List<String> courseCodes) {
        List<String> oldCourseCodes = this.courseCodes;
        this.courseCodes = courseCodes;
        support.firePropertyChange("courseCodes", oldCourseCodes, courseCodes);
    }

    /**
     * Adds a property change listener to receive notifications of property changes.
     *
     * @param listener the listener to be added
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Removes a property change listener.
     *
     * @param listener the listener to be removed
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
}
