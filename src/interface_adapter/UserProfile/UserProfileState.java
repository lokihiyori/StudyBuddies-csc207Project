package interface_adapter.UserProfile;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class UserProfileState {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private String name;
    private String email;
    private String creationTime;
    private List<String> courseCodes;

    public void setName(String name) {
        String oldName = this.name;
        this.name = name;
        support.firePropertyChange("name", oldName, name);
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        String oldEmail = this.email;
        this.email = email;
        support.firePropertyChange("email", oldEmail, email);
    }

    public String getEmail() {
        return email;
    }

    public void setCreationTime(String creationTime) {
        String oldCreationTime = this.creationTime;
        this.creationTime = creationTime;
        support.firePropertyChange("creationTime", oldCreationTime, creationTime);
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCourseCodes(List<String> courseCodes) {
        List<String> oldCourseCodes = this.courseCodes;
        this.courseCodes = courseCodes;
        support.firePropertyChange("courseCodes", oldCourseCodes, courseCodes);
    }

    public List<String> getCourseCodes() {
        return courseCodes;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
}