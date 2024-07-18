package interface_adapter.CreateGroupChat;

import usecase.CreateGroupChat.CreateGroupChatInputBoundary;
import usecase.CreateGroupChat.CreateGroupChatInputData;

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
