package interface_adapter.GetMyGroupChat;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GetMyGroupChatViewModel extends ViewModel {

    private GetMyGroupChatState state;
    public GetMyGroupChatViewModel(String viewName) {
        super("My GroupChat List");
    }

    public void setState(GetMyGroupChatState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public GetMyGroupChatState getState() {
        return state;
    }


}
