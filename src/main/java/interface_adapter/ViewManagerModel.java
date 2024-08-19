package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Manages the currently active view and supports property change notifications.
 */
public class ViewManagerModel {

    private String activeViewName;

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Gets the name of the currently active view.
     *
     * @return the name of the active view
     */
    public String getActiveView() {
        return activeViewName;
    }

    /**
     * Sets the name of the currently active view.
     *
     * @param activeView the name of the new active view
     */
    public void setActiveView(String activeView) {
        this.activeViewName = activeView;
    }

    /**
     * Fires a property change event to notify listeners that the active view has changed.
     */
    public void firePropertyChanged() {
        support.firePropertyChange("view", null, this.activeViewName);
    }

    /**
     * Adds a property change listener to this model.
     *
     * @param listener the property change listener to add
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
