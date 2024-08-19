package interface_adapter.CreateGroupChat;

import use_case.CreateGroupChat.CreateGroupChatInputBoundary;
import use_case.CreateGroupChat.CreateGroupChatInputData;

import java.io.IOException;

/**
 * Controller for handling the creation of a group chat.
 * It interacts with the use case layer to execute the creation of a group chat
 * based on the provided input data.
 */
public class CreateGroupChatController {
    final CreateGroupChatInputBoundary createGroupChatucInteractor;


    /**
     * Constructs a new CreateGroupChatController with the specified input boundary.
     *
     * @param createGroupChatucInteractor the input boundary for creating a group chat
     */

    public CreateGroupChatController(CreateGroupChatInputBoundary createGroupChatucInteractor){
        this.createGroupChatucInteractor = createGroupChatucInteractor;
    }
    /**
     * Executes the creation of a group chat with the specified code.
     * This method creates an instance of {@link CreateGroupChatInputData} with the given code
     * and passes it to the input boundary for processing.
     *
     * @param code the code for the new group chat
     * @throws IOException if an I/O error occurs during the creation process
     */
    public void executeCreateGroupChat(String code) throws IOException {
        CreateGroupChatInputData createGroupChatInputData = new CreateGroupChatInputData(code);
        createGroupChatucInteractor.execute(createGroupChatInputData);
    }
}
