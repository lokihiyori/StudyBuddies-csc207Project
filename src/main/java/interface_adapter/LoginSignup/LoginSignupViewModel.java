package interface_adapter.LoginSignup;

import interface_adapter.ViewModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoginSignupViewModel extends ViewModel {
    private LoginSignupState state = new LoginSignupState();

    public LoginSignupViewModel() {super("LoginSignupView");}

    public void setState(LoginSignupState state) {
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

    public LoginSignupState getState() {
        return state;
    }
}

