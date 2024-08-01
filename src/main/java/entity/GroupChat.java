package entity;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;

public class GroupChat {
    private String code;
    private ArrayList<CommonUser> GroupMembers;
    private Dictionary<CommonUser, String> MessageHistory;
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

    public void setCode(String code) {
        this.code = code;
    }

    public void setGroupMembers(ArrayList<CommonUser> groupMembers) {
        GroupMembers = groupMembers;
    }

    public void setMessageHistory(Dictionary<CommonUser, String> messageHistory) {
        MessageHistory = messageHistory;
    }

    public String getCode() {
        return code;
    }

    public ArrayList<CommonUser> getGroupMembers() {
        return GroupMembers;
    }

    public Dictionary<CommonUser, String> getMessageHistory() {
        return MessageHistory;
    }
}
