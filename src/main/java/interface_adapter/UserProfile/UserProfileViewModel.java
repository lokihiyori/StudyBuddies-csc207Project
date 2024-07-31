package interface_adapter.UserProfile;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class UserProfileViewModel {
    private String name;
    private String email;
    private String creationTime;
    private List<String> courseCodes;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    public void setName(String name) {
        String oldName = this.name;
        this.name = name;
        support.firePropertyChange("name", oldName, name);
    }

    public void setEmail(String email) {
        String oldEmail = this.email;
        this.email = email;
        support.firePropertyChange("email", oldEmail, email);
    }

    public void setCreationTime(String creationTime) {
        String oldCreationTime = this.creationTime;
        this.creationTime = creationTime;
        support.firePropertyChange("creationTime", oldCreationTime, creationTime);
    }

    public void setCourseCodes(List<String> courseCodes) {
        List<String> oldCourseCodes = this.courseCodes;
        this.courseCodes = courseCodes;
        support.firePropertyChange("courseCodes", oldCourseCodes, courseCodes);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
}
