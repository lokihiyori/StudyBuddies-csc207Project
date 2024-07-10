package entity;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;

public class GroupChat {
    private String code;
    private ArrayList<Users> GroupMembers;
    private Dictionary<Users, String> MessageHistory;
    GroupChat(String Code){
        this.code = Code;
        this.GroupMembers = new ArrayList<>();
        this.MessageHistory = new Dictionary<Users, String>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public Enumeration<Users> keys() {
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
            public String put(Users key, String value) {
                return null;
            }

            @Override
            public String remove(Object key) {
                return null;
            }
        };
    }


}
