package interface_adapter.CreateGroupChat;

import entity.Users;
import usecase.CreateGroupChat.CreateGroupChatInputBoundary;
import usecase.CreateGroupChat.CreateGroupChatInputData;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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
