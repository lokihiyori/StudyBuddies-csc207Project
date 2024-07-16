package interface_adapter.CreateGroupChat;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class CreateGroupChatViewModel extends ViewModel {
    private CreateGroupChatState state = new CreateGroupChatState();

    public CreateGroupChatViewModel() {
        super("create group chat");
    }

    public void setState(CreateGroupChatState state) {
        this.state = state;
    }
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    @Override
    public void firePropertyChanged() {support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
    public CreateGroupChatState getState() {
        return state;
    }
}
