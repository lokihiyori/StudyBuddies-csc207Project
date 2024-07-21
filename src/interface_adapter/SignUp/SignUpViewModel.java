package interface_adapter.SignUp;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SignUpViewModel extends ViewModel {

    public final String TITLE_LABEL = "Sign Up";
    public final String USERNAME_LABEL = "Choose username";
    public final String EMAIL_LABEL = "Choose email";
    public final String PASSWORD_LABEL = "Choose password";
    public final String REPEAT_PASSWORD_LABEL = "Enter password again";

    public final String SIGNUP_BUTTON_LABEL = "Sign up";
    public final String CANCEL_BUTTON_LABEL = "Cancel";

    private SignUpState state = new SignUpState();

    public SignUpViewModel() {
        super("sign up");
    }

    public void setState(SignUpState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Signup Presenter will call to let the ViewModel know
    // to alert the View
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public SignUpState getState() {
        return state;
    }
}
