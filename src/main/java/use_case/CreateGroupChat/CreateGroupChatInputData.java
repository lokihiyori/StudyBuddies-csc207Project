package use_case.CreateGroupChat;

import entity.CommonUser;

import java.util.List;

/**
 * The CreateGroupChatInputData class is a data structure that holds the necessary
 * information required to create a new group chat. This currently includes the code
 * associated with the group chat.
 */
public class CreateGroupChatInputData {
    final private String code;

    /**
     * Constructs a new CreateGroupChatInputData instance with the specified group chat code.
     *
     * @param code the code associated with the group chat to be created.
     */
    public CreateGroupChatInputData(String code) {
        this.code = code;
    }

    /**
     * Returns the code associated with the group chat.
     *
     * @return the group chat code.
     */
    public String getCode() {
        return code;
    }
}
