package interface_adapter.UserProfile;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class UserProfileState {
    private String name;
    private String email;
    private String creationTime;
    private List<String> courseCodes;
    private final PropertyChangeSupport support;
    public UserProfileState() {
        support = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        support.firePropertyChange("name", this.name, this.name = name);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        support.firePropertyChange("email", this.email, this.email = email);
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        support.firePropertyChange("creationTime", this.creationTime, this.creationTime = creationTime);
    }

    public List<String> getCourseCodes() {
        return courseCodes;
    }

    public void setCourseCodes(List<String> courseCodes) {
        support.firePropertyChange("courseCodes", this.courseCodes, this.courseCodes = courseCodes);
    }
}
