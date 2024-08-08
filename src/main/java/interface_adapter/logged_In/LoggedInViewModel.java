package interface_adapter.logged_In;

import entity.CommonUser;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoggedInViewModel extends ViewModel {
    public static final String CREATE_EVENT_BUTTON_LABEL = "Create Event";
    public final String TITLE_LABEL = "Logged In View";
    public static final String LOGOUT_BUTTON_LABEL = "Log out";

    private LoggedInState state = new LoggedInState();
    //private final PropertyChangeSupport support;
    private CommonUser currentUser;
    private String loggedInUser;

    public LoggedInViewModel()  {
        super("logged in");
    }
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);



    public void setState(LoggedInState state) {
        LoggedInState oldState = this.state;
        this.state = state;
        support.firePropertyChange("state", oldState, this.state);
    }

    public LoggedInState getState() {
        return state;
    }

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }


    public String getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(String loggedInUser) {
        String oldLoggedInUser = this.loggedInUser;
        this.loggedInUser = loggedInUser;
        support.firePropertyChange("loggedInUser", oldLoggedInUser, loggedInUser);
    }


}