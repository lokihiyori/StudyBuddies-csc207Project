package interface_adapter.SignUp;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
/**
 * ViewModel for managing the state and properties of the sign-up view.
 */
public class SignUpViewModel extends ViewModel {

    public final String TITLE_LABEL = "Sign Up";
    public final String USERNAME_LABEL = "Choose username";
    public final String EMAIL_LABEL = "Choose email";
    public final String PASSWORD_LABEL = "Choose password";
    public final String REPEAT_PASSWORD_LABEL = "Enter password again";

    public final String SIGNUP_BUTTON_LABEL = "Sign up";
    public final String LOGIN_BUTTON_LABEL = "Login";

    private SignUpState state = new SignUpState();

    /**
     * Constructs a {@code SignUpViewModel} with a view name of "sign up".
     */
    public SignUpViewModel() {
        super("sign up");
    }
    /**
     * Sets the state of the sign-up view model.
     *
     * @param state the new state to set
     */
    public void setState(SignUpState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Signup Presenter will call to let the ViewModel know
    // to alert the View
    /**
     * Notifies listeners of changes in the state of the sign-up view model.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Adds a listener to be notified of changes in the state of the sign-up view model.
     *
     * @param listener the listener to add
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Returns the current state of the sign-up view model.
     *
     * @return the current state
     */
    public SignUpState getState() {
        return state;
    }
}
