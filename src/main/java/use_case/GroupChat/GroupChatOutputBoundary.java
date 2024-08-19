package use_case.GroupChat;

/**
 * GroupChatOutputBoundary defines the output methods for presenting group chat data.
 * It is responsible for presenting the results of the group chat use case to the user.
 */
public interface GroupChatOutputBoundary {

    /**
     * Presents the processed message to the user.
     *
     * @param outputData the data containing the message to be presented.
     */
    void presentMessage(GroupChatOutputData outputData);
}
