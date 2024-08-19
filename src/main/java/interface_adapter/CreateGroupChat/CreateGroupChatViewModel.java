package interface_adapter.CreateGroupChat;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * ViewModel for managing the state and property change events of the group chat creation process.
 * This class handles the state of the group chat creation and notifies listeners of any changes.
 */
public class CreateGroupChatViewModel{
    private CreateGroupChatState state = new CreateGroupChatState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    /**
     * Sets the current state of the group chat creation process.
     * Notifies listeners of the state change.
     *
     * @param state the new state to set
     */
    public void setState(CreateGroupChatState state) {
        CreateGroupChatState oldState = this.state;
        this.state = state;
        support.firePropertyChange("state", oldState, this.state);
    }

    /**
     * Returns the current state of the group chat creation process.
     *
     * @return the current CreateGroupChatState
     */
    public CreateGroupChatState getState() {
        return state;
    }

    /**
     * Adds a PropertyChangeListener to receive notifications of state changes.
     *
     * @param listener the PropertyChangeListener to add
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    /**
     * Removes a PropertyChangeListener so it no longer receives notifications of state changes.
     *
     * @param listener the PropertyChangeListener to remove
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
}
