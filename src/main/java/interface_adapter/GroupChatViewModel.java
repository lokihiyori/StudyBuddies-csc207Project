package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashSet;
import java.util.Set;

/**
 * ViewModel for managing group chat functionality within courses.
 * Maintains a set of courses and group chats and supports property change notifications.
 */
public class GroupChatViewModel {

    private final Set<String> courses;
    private final Set<String> groupChats;
    private final PropertyChangeSupport support;

    /**
     * Constructs a GroupChatViewModel with initialized sets for courses and group chats.
     * Adds a default course "CSC207" for demonstration purposes.
     */
    public GroupChatViewModel() {
        this.courses = new HashSet<>();
        this.groupChats = new HashSet<>();
        this.support = new PropertyChangeSupport(this);

        // For demonstration, we assume the course "CSC207" exists
        courses.add("CSC207");
    }


    /**
     * Joins a group chat for a specified course and notifies listeners of the change.
     *
     * @param course the course for which the group chat is joined
     */
    public void joinGroupChat(String course) {
        support.firePropertyChange("groupChatJoined", null, course);
    }


    /**
     * Adds a property change listener to receive notifications about property changes.
     *
     * @param pcl the property change listener to be added
     */
    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

}