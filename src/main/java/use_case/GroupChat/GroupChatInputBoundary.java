package use_case.GroupChat;

/**
 * GroupChatInputBoundary defines the input methods for interacting with the group chat use case.
 * It is responsible for processing user input related to sending messages.
 */
public interface GroupChatInputBoundary {

    /**
     * Processes and sends a message to the group chat.
     *
     * @param inputData the data containing the message and the username of the sender.
     */
    void sendMessage(GroupChatInputData inputData);
}
