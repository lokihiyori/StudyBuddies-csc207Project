package entity;

import java.util.ArrayList;
import java.util.List;
/**
 * A factory class for creating instances of {@link GroupChat}.
 */
public class GroupChatFactory {
    /**
     * Creates a new {@link GroupChat} with the specified course code.
     *
     * @param code the code of the course associated with the group chat
     * @return     a new instance of {@link GroupChat}
     */
    public GroupChat create(String code){ return new GroupChat(code);};
}
