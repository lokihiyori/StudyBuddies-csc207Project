package interface_adapter.GroupChat;

import use_case.GroupChat.GroupChatInputBoundary;
import use_case.GroupChat.GroupChatInputData;

/**
 * GroupChatController handles user input related to the group chat.
 * It interacts with the GroupChatInputBoundary to process and send messages.
 */
public class GroupChatController {
    private final GroupChatInputBoundary inputBoundary;

    /**
     * Constructs a GroupChatController with the specified input boundary.
     *
     * @param inputBoundary the input boundary for processing group chat messages.
     */
    public GroupChatController(GroupChatInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    /**
     * Handles sending a message from the user to the group chat.
     *
     * @param message the message to be sent.
     * @param username the username of the sender.
     */
    public void handleSendMessage(String message, String username) {
        GroupChatInputData inputData = new GroupChatInputData(message, username);
        inputBoundary.sendMessage(inputData);
    }
}
