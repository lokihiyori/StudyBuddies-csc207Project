package interface_adapter.CreateGroupChat;

import use_case.CreateGroupChat.CreateGroupChatInputBoundary;
import use_case.CreateGroupChat.CreateGroupChatInputData;

public class CreateGroupChatController {
    final CreateGroupChatInputBoundary createGroupChatucInteractor;

    public CreateGroupChatController(CreateGroupChatInputBoundary createGroupChatucInteractor){
        this.createGroupChatucInteractor = createGroupChatucInteractor;
    }
    public void executeCreateGroupChat(String code){
        CreateGroupChatInputData createGroupChatInputData = new CreateGroupChatInputData(code);
        createGroupChatucInteractor.execute(createGroupChatInputData);
    }
}
