package interface_adapter.login;
import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Represents the ViewModel for the login view. This class manages the state related to user login,
 * including the username, password, and any error messages. It also supports property change notifications
 * for updating the view.
 */
public class LoginViewModel extends ViewModel {

    public final String TITLE_LABEL = "Log In View";
    public final String USERNAME_LABEL = "Enter username";
    public final String PASSWORD_LABEL = "Enter password";

    public final String LOGIN_BUTTON_LABEL = "Log in";
    public final String CANCEL_BUTTON_LABEL = "Cancel";

    private LoginState state = new LoginState();

    /**
     * Constructs a new instance of LoginViewModel with a default view name of "log in".
     */
    public LoginViewModel() {
        super("log in");
    }

    /**
     * Sets the state of the LoginViewModel.
     *
     * @param state the new state to set
     */
    public void setState(LoginState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Signup Presenter will call to let the ViewModel know
    // to alert the View

    /**
     * Notifies all registered listeners of changes to the state.
     * This method should be called when the state changes to update the view.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Adds a property change listener to be notified of state changes.
     *
     * @param listener the PropertyChangeListener to add
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Gets the current state of the LoginViewModel.
     *
     * @return the current LoginState
     */
    public LoginState getState() {
        return state;
    }
}
