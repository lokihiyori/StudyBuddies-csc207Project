package interface_adapter.logged_In;

import entity.CommonUser;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoggedInViewModel {
    private final PropertyChangeSupport support;
    private CommonUser currentUser;

    public LoggedInViewModel() {
        support = new PropertyChangeSupport(this);
    }

    public void setCurrentUser(CommonUser currentUser) {
        CommonUser oldUser = this.currentUser;
        this.currentUser = currentUser;
        support.firePropertyChange("currentUser", oldUser, currentUser);
    }

    public CommonUser getCurrentUser() {
        return currentUser;
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }
}
