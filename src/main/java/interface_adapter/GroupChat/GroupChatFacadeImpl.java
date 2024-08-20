package interface_adapter.GroupChat;


import SocketIO.GroupChatClient;
import use_case.GroupChat.GroupChatInteractor;
import use_case.GroupChat.GroupChatInputBoundary;
import use_case.GroupChat.GroupChatOutputBoundary;

/**
 * GroupChatFacadeImpl is the implementation of the GroupChatFacade interface.
 * It handles the setup and initialization of the group chat system, including
 * the use case, controller, and presenter layers.
 */
public class GroupChatFacadeImpl implements GroupChatFacade {

    /**
     * Starts the group chat client and connects it to the specified server.
     *
     * @param courseName the name of the course associated with the group chat.
     * @param host the host address of the chat server.
     * @param port the port number of the chat server.
     */
    @Override
    public void startGroupChat(String courseName, String host, int port) {
        // Create the presenter
        GroupChatOutputBoundary outputBoundary = new GroupChatPresenter();

        // Create the interactor
        GroupChatInputBoundary interactor = new GroupChatInteractor(outputBoundary);

        // Create the controller
        GroupChatController controller = new GroupChatController(interactor);

        // Set up the client and connect to the server
        GroupChatClient client = new GroupChatClient();
        client.startChat(courseName, host, port);

        // Example of sending a message through the controller
        controller.handleSendMessage("Hello, everyone!", "JohnDoe");
    }
}