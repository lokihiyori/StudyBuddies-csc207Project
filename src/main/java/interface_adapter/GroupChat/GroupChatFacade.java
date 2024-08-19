package interface_adapter.GroupChat;

/**
 * GroupChatFacade provides a high-level interface for interacting with the group chat system.
 * It abstracts the complexities of starting the chat client and connecting to the server.
 */
public interface GroupChatFacade {

    /**
     * Starts the group chat client and connects it to the specified server.
     *
     * @param courseName the name of the course associated with the group chat.
     * @param host the host address of the chat server.
     * @param port the port number of the chat server.
     */
    void startGroupChat(String courseName, String host, int port);
}
