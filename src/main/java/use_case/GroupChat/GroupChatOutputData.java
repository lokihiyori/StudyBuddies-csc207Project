package use_case.GroupChat;

/**
 * GroupChatOutputData contains the data that is output from the group chat use case.
 * This includes the processed message that is ready to be presented to the user.
 */
public class GroupChatOutputData {
    private final String message;

    /**
     * Constructs a GroupChatOutputData instance with the specified message.
     *
     * @param message the processed message content.
     */
    public GroupChatOutputData(String message) {
        this.message = message;
    }

    /**
     * Returns the processed message content.
     *
     * @return the message content.
     */
    public String getMessage() {
        return message;
    }
}
