package interface_adapter.CreateGroupChat;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CreateGroupChatViewModel{
    private CreateGroupChatState state = new CreateGroupChatState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void setState(CreateGroupChatState state) {
        CreateGroupChatState oldState = this.state;
        this.state = state;
        support.firePropertyChange("state", oldState, this.state);
    }

    public CreateGroupChatState getState() {
        return state;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
}
