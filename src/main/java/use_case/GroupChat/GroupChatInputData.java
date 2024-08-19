package use_case.GroupChat;

/**
 * GroupChatInputData contains the data required to send a message in the group chat.
 * This includes the message content and the username of the sender.
 */
public class GroupChatInputData {
    private final String message;
    private final String username;

    /**
     * Constructs a GroupChatInputData instance with the specified message and username.
     *
     * @param message the content of the message.
     * @param username the username of the sender.
     */
    public GroupChatInputData(String message, String username) {
        this.message = message;
        this.username = username;
    }

    /**
     * Returns the content of the message.
     *
     * @return the message content.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Returns the username of the sender.
     *
     * @return the username.
     */
    public String getUsername() {
        return username;
    }
}
