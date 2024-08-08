package interface_adapter;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.HashSet;
import java.util.Set;

public class GroupChatViewModel {

    private final Set<String> courses;
    private final Set<String> groupChats;
    private final PropertyChangeSupport support;

    public GroupChatViewModel() {
        this.courses = new HashSet<>();
        this.groupChats = new HashSet<>();
        this.support = new PropertyChangeSupport(this);

        // For demonstration, we assume the course "CSC207" exists
        courses.add("CSC207");
    }


    public void joinGroupChat(String course) {
        support.firePropertyChange("groupChatJoined", null, course);
    }

    public void addPropertyChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

}