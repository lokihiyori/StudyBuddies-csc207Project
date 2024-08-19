package interface_adapter.logged_In;

import entity.CommonUser;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Represents the ViewModel for the logged-in user view.
 * Manages the state of the logged-in view and provides methods to interact with the view's state.
 */
public class LoggedInViewModel extends ViewModel {
    /** Label for the "Create Event" button. */
    public static final String CREATE_EVENT_BUTTON_LABEL = "Create Event";
    /** Title label for the logged-in view. */
    public final String TITLE_LABEL = "Logged In View";
    /** Label for the "Log out" button. */
    public static final String LOGOUT_BUTTON_LABEL = "Log out";

    private LoggedInState state = new LoggedInState();
    //private final PropertyChangeSupport support;
    private CommonUser currentUser;
    private String loggedInUser;
    /**
     * Constructs a new instance of LoggedInViewModel with default values.
     */
    public LoggedInViewModel()  {
        super("logged in");
    }
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Sets the state of the logged-in view model.
     *
     * @param state the new state to set
     */

    public void setState(LoggedInState state) {
        LoggedInState oldState = this.state;
        this.state = state;
        support.firePropertyChange("state", oldState, this.state);
    }
    /**
     * Gets the current state of the logged-in view model.
     *
     * @return the current state
     */
    public LoggedInState getState() {
        return state;
    }
    /**
     * Notifies all registered listeners that the state has changed.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }
    /**
     * Adds a property change listener to be notified of changes.
     *
     * @param listener the listener to add
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Gets the username of the currently logged-in user.
     *
     * @return the logged-in user's username
     */
    public String getLoggedInUser() {
        return loggedInUser;
    }
    /**
     * Sets the username of the currently logged-in user.
     *
     * @param loggedInUser the new username to set
     */
    public void setLoggedInUser(String loggedInUser) {
        String oldLoggedInUser = this.loggedInUser;
        this.loggedInUser = loggedInUser;
        support.firePropertyChange("loggedInUser", oldLoggedInUser, loggedInUser);
    }


}