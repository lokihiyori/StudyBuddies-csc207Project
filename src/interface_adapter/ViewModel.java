package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public abstract class ViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private String viewName;
    private String message;

    public ViewModel(String viewName) {
        this.viewName = viewName;
    }
    public String getViewName() {
        return this.viewName;
    }

    public abstract void firePropertyChanged();
    public abstract void addPropertyChangeListener(PropertyChangeListener listener);


    public void setMessage(String s) {
        String oldMessage = this.message;
        this.message = message;
        support.firePropertyChange("message", oldMessage, message);
    }}