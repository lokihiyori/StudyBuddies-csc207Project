package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoginViewModel {
    public static final String LOGIN_BUTTON_LABEL = "Login";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    private LoginState state;
    private final PropertyChangeSupport support;

    public LoginViewModel() {
        this.state = new LoginState();
        this.support = new PropertyChangeSupport(this);
    }

    public LoginState getState() {
        return state;
    }

    public void setState(LoginState state) {
        LoginState oldState = this.state;
        this.state = state;
        support.firePropertyChange("state", oldState, state);
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public void removePropertyChangeListener(PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
    }
}
