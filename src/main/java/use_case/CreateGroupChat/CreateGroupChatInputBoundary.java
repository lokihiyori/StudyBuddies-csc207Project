package use_case.CreateGroupChat;

import java.io.IOException;

/**
 * The CreateGroupChatInputBoundary interface defines the method required for handling
 * the input data when creating a new group chat. Implementations of this interface
 * are responsible for processing the provided group chat input data and executing
 * the necessary operations to create the group chat.
 */
public interface CreateGroupChatInputBoundary {

    /**
     * Executes the group chat creation process using the provided input data.
     * Implementations should handle the logic for validating and processing the
     * group chat data to create a new group chat in the system.
     *
     * @param createGroupChatInputData the data required to create a new group chat.
     * @throws IOException if there is an error during the group chat creation process.
     */
    void execute(CreateGroupChatInputData createGroupChatInputData) throws IOException;
}
