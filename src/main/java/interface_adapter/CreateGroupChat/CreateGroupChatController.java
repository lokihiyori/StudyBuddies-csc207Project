package interface_adapter.CreateGroupChat;

import use_case.CreateGroupChat.CreateGroupChatInputBoundary;
import use_case.CreateGroupChat.CreateGroupChatInputData;

import java.io.IOException;

public class CreateGroupChatController {
    final CreateGroupChatInputBoundary createGroupChatucInteractor;

    public CreateGroupChatController(CreateGroupChatInputBoundary createGroupChatucInteractor){
        this.createGroupChatucInteractor = createGroupChatucInteractor;
    }
    public void executeCreateGroupChat(String code) throws IOException {
        CreateGroupChatInputData createGroupChatInputData = new CreateGroupChatInputData(code);
        createGroupChatucInteractor.execute(createGroupChatInputData);
    }
}
