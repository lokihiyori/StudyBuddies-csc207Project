package usecase.GetMyGroupChat;

import data_access.UserDataAccessObject;
import entity.GroupChat;
import entity.User;
import entity.Users;

import java.util.List;

public class GetMyGroupChatInteractor {
    final Users userDataAccessObject;
    final GetMyGroupChatOutPutBoundary getMyGroupChatOutPutBoundary;

    public GetMyGroupChatInteractor(Users userDataAccessObject, GetMyGroupChatOutPutBoundary getMyGroupChatOutPutBoundary){
        this.userDataAccessObject = userDataAccessObject;
        this.getMyGroupChatOutPutBoundary = getMyGroupChatOutPutBoundary;
    }

    public void execute(GetMyGroupChatInputData getMyGroupChatInputData) {
        User user = userDataAccessObject.get(getMyGroupChatInputData.getUsers());
        List<GroupChat> groupChatList = userDataAccessObject.getGroupChatList();
        GetMyGroupChatOutputData outputData = new GetMyGroupChatOutputData(user, groupChatList);
        GetMyGroupChatOutPutBoundary.prepareSuccessView(outputData);
    }
}
