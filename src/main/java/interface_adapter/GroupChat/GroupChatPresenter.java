package interface_adapter.GroupChat;

import use_case.GroupChat.GroupChatOutputBoundary;
import use_case.GroupChat.GroupChatOutputData;

/**
 * GroupChatPresenter is responsible for presenting the group chat messages to the user.
 * It implements the GroupChatOutputBoundary interface and handles the output data.
 */
public class GroupChatPresenter implements GroupChatOutputBoundary {

    /**
     * Presents the group chat message by printing it to the console.
     *
     * @param outputData the data containing the message to be presented.
     */
    @Override
    public void presentMessage(GroupChatOutputData outputData) {
        // Directly display the message, for example, to the console
        System.out.println("New message: " + outputData.getMessage());
    }
}
