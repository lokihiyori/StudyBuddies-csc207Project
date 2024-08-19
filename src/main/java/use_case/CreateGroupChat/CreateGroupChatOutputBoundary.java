package use_case.CreateGroupChat;

/**
 * The CreateGroupChatOutputBoundary interface defines the methods required for handling
 * the output of the group chat creation process. Implementations of this interface
 * should provide the logic for preparing views or responses for both successful
 * and failed group chat creation attempts.
 */
public interface CreateGroupChatOutputBoundary {

    /**
     * Prepares the view or response to be shown when the group chat creation process
     * is successful. Implementations should define how the success information,
     * including the group chat data, is presented.
     *
     * @param createGroupChatOutputData the data related to the group chat that was successfully created.
     */
    void prepareSuccessView(CreateGroupChatOutputData createGroupChatOutputData);

    /**
     * Prepares the view or response to be shown when the group chat creation process
     * fails. Implementations should define how the error message is presented.
     *
     * @param error the error message explaining why the group chat creation failed.
     */
    void prepareFailView(String error);
}
