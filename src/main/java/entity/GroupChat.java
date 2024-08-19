package entity;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
/**
 * Represents a group chat associated with a specific course, identified by a code.
 * Each group chat contains a list of members and a message history.
 */
public class GroupChat {
    private String code;
    private ArrayList<CommonUser> GroupMembers;
    private Dictionary<CommonUser, String> MessageHistory;
    /**
     * Constructs a new GroupChat with the specified course code.
     * Initializes the group members list and message history.
     *
     * @param Code the code of the course associated with the group chat
     */
    public GroupChat(String Code){
        this.code = Code;

        this.GroupMembers = new ArrayList<>();
        this.MessageHistory = new Dictionary<>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public Enumeration<CommonUser> keys() {
                return null;
            }

            @Override
            public Enumeration<String> elements() {
                return null;
            }

            @Override
            public String get(Object key) {
                return null;
            }

            @Override
            public String put(CommonUser key, String value) {
                return null;
            }

            @Override
            public String remove(Object key) {
                return null;
            }
        };
    }

    /**
     * Sets the code of the group chat.
     *
     * @param code the new code of the group chat
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Sets the list of group members.
     *
     * @param groupMembers the new list of group members
     */
    public void setGroupMembers(ArrayList<CommonUser> groupMembers) {
        GroupMembers = groupMembers;
    }

    /**
     * Sets the message history of the group chat.
     *
     * @param messageHistory the new message history of the group chat
     */
    public void setMessageHistory(Dictionary<CommonUser, String> messageHistory) {
        MessageHistory = messageHistory;
    }

    /**
     * Returns the code of the group chat.
     *
     * @return the code of the group chat
     */
    public String getCode() {
        return code;
    }

    /**
     * Returns the list of group members.
     *
     * @return the list of group members
     */
    public ArrayList<CommonUser> getGroupMembers() {
        return GroupMembers;
    }

    /**
     * Returns the message history of the group chat.
     *
     * @return the message history of the group chat
     */
    public Dictionary<CommonUser, String> getMessageHistory() {
        return MessageHistory;
    }
}
