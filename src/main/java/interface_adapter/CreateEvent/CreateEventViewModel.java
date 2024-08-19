package interface_adapter.CreateEvent;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
/**
 * ViewModel for managing the state and properties related to creating an event.
 * It extends the {@link ViewModel} class and supports property change notifications
 * for updates related to event creation.
 */
public class CreateEventViewModel extends ViewModel {
    public static final String TITLE_LABEL = "Event details";
    public static final String EVENT_NAME_LABEL = "Choose event name";
    public static final String EVENT_PLACE_LABEL = "Choose event place";
    public static final String EVENT_DATE_LABEL = "Choose event date";
    public static final String EVENT_TIME_LABEL = "Choose event time";
    public static final String EVENT_TYPE_LABEL = "Choose event type";
    public static final String CREATE_EVENT_LABEL = "create";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    private CreateEventState state = new CreateEventState();

    /**
     * Constructs a new CreateEventViewModel with the default view name "CreateEventView".
     */
    public CreateEventViewModel() {super("CreateEventView");}

    /**
     * Sets the current state of the event creation process and notifies listeners of the change.
     *
     * @param state the new state of the event creation
     */
    public void setState(CreateEventState state) {
        CreateEventState oldState = this.state;
        this.state = state;
        support.firePropertyChange("state", oldState, state);
    }

    /**
     * Notifies listeners that the event list has been updated.
     */
    // Specific method to notify about the event list update
    public void notifyEventAdded() {
        support.firePropertyChange("eventsUpdated", false, true);
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    // This is what the Signup Presenter will call to let the ViewModel know
    // to alert the View
    /**
     * Fires a property change event to notify listeners of any changes to the state.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * Adds a PropertyChangeListener to listen for changes in the ViewModel's properties.
     *
     * @param listener the PropertyChangeListener to be added
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Returns the current state of the event creation process.
     *
     * @return the current CreateEventState
     */
    public CreateEventState getState() {
        return state;
    }
}