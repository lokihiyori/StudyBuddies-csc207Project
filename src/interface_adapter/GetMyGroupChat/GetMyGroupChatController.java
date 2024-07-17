package interface_adapter.GetMyGroupChat;

import entity.Users;
import usecase.CreateGroupChat.CreateGroupChatInputData;
import usecase.GetMyGroupChat.GetMyGroupChatInputBoundary;
import usecase.GetMyGroupChat.GetMyGroupChatInputData;

public class GetMyGroupChatController {
    final GetMyGroupChatInputBoundary getMyGroupChatInteractor;

    public GetMyGroupChatController(GetMyGroupChatInputBoundary getMyGroupChatInteractor){
        this.getMyGroupChatInteractor = getMyGroupChatInteractor;
    }
    public void executeGetMyGroupChatController(Users users){
        GetMyGroupChatInputData getMyGroupChatInputData = new GetMyGroupChatInputData(users);
        getMyGroupChatInteractor.execute(getMyGroupChatInputData);
    }
}
