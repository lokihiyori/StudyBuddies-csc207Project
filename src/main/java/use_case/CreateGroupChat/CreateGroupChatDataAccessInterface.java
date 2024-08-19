package use_case.CreateGroupChat;

import entity.GroupChat;

import java.io.IOException;

/**
 * The CreateGroupChatDataAccessInterface defines the methods required for interacting
 * with the data storage system when creating and retrieving group chat information.
 * Implementations of this interface are responsible for handling the persistence
 * of group chat data and retrieving group chat details by code.
 */
public interface CreateGroupChatDataAccessInterface {

    /**
     * Saves the given group chat to the data storage system.
     * Implementations should handle the persistence logic for storing the group chat information.
     *
     * @param groupChat the GroupChat object to be saved.
     * @throws IOException if there is an error while saving the group chat.
     */
    public void saveGroupChat(GroupChat groupChat) throws IOException;

    /**
     * Retrieves the group chat associated with the specified code from the data storage system.
     *
     * @param code the code of the group chat to retrieve.
     * @return the GroupChat object associated with the given code.
     */
    public GroupChat getGroupChat(String code);

    /**
     * Checks if a group chat with the specified identifier (code) already exists in the data storage system.
     *
     * @param identifier the identifier (code) of the group chat to check for existence.
     * @return true if a group chat with the given identifier exists, false otherwise.
     */
    boolean existsByCode(String identifier);
}
