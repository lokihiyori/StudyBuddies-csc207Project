package entity;

import java.util.ArrayList;
import java.util.List;

public class GroupChatFactory {
    public static GroupChat create(String code){ return new GroupChat(code);};
}
