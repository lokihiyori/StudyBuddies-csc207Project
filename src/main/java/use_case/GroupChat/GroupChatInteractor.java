package use_case.GroupChat;

/**
 * GroupChatInteractor implements the group chat use case.
 * It processes input data and uses the output boundary to present results.
 */
public class GroupChatInteractor implements GroupChatInputBoundary {
    private final GroupChatOutputBoundary outputBoundary;

    /**
     * Constructs a GroupChatInteractor with the specified output boundary.
     *
     * @param outputBoundary the boundary for presenting output data.
     */
    public GroupChatInteractor(GroupChatOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    /**
     * Processes and sends a message to the group chat.
     *
     * @param inputData the data containing the message and the username of the sender.
     */
    @Override
    public void sendMessage(GroupChatInputData inputData) {
        // Logic to process the message (e.g., validation, transformation)
        String processedMessage = inputData.getUsername() + ": " + inputData.getMessage();

        // Creating output data
        GroupChatOutputData outputData = new GroupChatOutputData(processedMessage);

        // Presenting the message
        outputBoundary.presentMessage(outputData);
    }
}
