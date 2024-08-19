package use_case.CreateGroupChat;

import entity.GroupChat;

/**
 * The CreateGroupChatOutputData class is a data structure that holds the information
 * related to the output of the group chat creation process. This includes the group chat code
 * and the created GroupChat object.
 */
public class CreateGroupChatOutputData {
    final private String code;
    final private GroupChat groupChat;

    /**
     * Constructs a new CreateGroupChatOutputData instance with the specified group chat code
     * and the created GroupChat object.
     *
     * @param code the code associated with the created group chat.
     * @param groupChat the GroupChat object that was created.
     */
    public CreateGroupChatOutputData(String code, GroupChat groupChat) {
        this.code = code;
        this.groupChat = groupChat;
    }

    /**
     * Returns the code associated with the created group chat.
     *
     * @return the group chat code.
     */
    public String getCode() {
        return code;
    }

    /**
     * Returns the GroupChat object that was created.
     *
     * @return the created GroupChat object.
     */
    public GroupChat getGroupChat() {
        return groupChat;
    }
}
