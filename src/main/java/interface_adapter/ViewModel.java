package interface_adapter;

import java.beans.PropertyChangeListener;

/**
 * Abstract base class for ViewModels that encapsulates the view's name
 * and provides methods for property change notifications.
 */
public abstract class ViewModel {

    private String viewName;

    /**
     * Constructs a ViewModel with the specified view name.
     *
     * @param viewName the name of the view associated with this ViewModel
     */
    public ViewModel(String viewName) {
        this.viewName = viewName;
    }

    /**
     * Gets the name of the view associated with this ViewModel.
     *
     * @return the name of the view
     */
    public String getViewName() {
        return this.viewName;
    }


    /**
     * Fires a property change event to notify listeners of changes in the ViewModel.
     * Subclasses must provide an implementation for this method.
     */
    public abstract void firePropertyChanged();

    /**
     * Adds a property change listener to this ViewModel.
     * Subclasses must provide an implementation for this method.
     *
     * @param listener the property change listener to add
     */
    public abstract void addPropertyChangeListener(PropertyChangeListener listener);


}
