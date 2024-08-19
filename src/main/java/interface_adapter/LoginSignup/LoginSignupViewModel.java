package interface_adapter.LoginSignup;

import interface_adapter.ViewModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * ViewModel for handling the state of the login/signup view. This class manages the state and
 * notifies listeners of any changes to the state.
 */
public class LoginSignupViewModel extends ViewModel {
    private LoginSignupState state = new LoginSignupState();

    /**
     * Constructs a new instance of LoginSignupViewModel with the default view name "LoginSignupView".
     */
    public LoginSignupViewModel() {super("LoginSignupView");}

    /**
     * Sets the state of this ViewModel.
     *
     * @param state the new state to set
     */
    public void setState(LoginSignupState state) {
        this.state = state;
    }

    /**
     * Notifies all registered listeners about changes to the state.
     */
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Signup Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Adds a PropertyChangeListener to this ViewModel. The listener will be notified of changes
     * to the state.
     *
     * @param listener the listener to add
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Gets the current state of this ViewModel.
     *
     * @return the current state
     */
    public LoginSignupState getState() {
        return state;
    }
}

