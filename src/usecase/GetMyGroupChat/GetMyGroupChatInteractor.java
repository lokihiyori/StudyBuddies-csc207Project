package usecase.GotoGroupChat;

import data_access.UserDataAccessObject;
import entity.User;

public class GotoGroupChatInteractor{
    final UserDataAccessObject userDataAccessObject;
    final GotoGroupChatOutPutBoundary gotoGroupChatOutPutBoundary;

    public GotoGroupChatInteractor(UserDataAccessObject userDataAccessObject, GotoGroupChatOutPutBoundary gotoGroupChatOutPutBoundary){
        this.userDataAccessObject = userDataAccessObject;
        this.gotoGroupChatOutPutBoundary = gotoGroupChatOutPutBoundary;
    }

    public void execute(GotoGroupChatInputData gotoGroupChatInputData) {
        User user = userDataAccessObject.get(gotoGroupChatInputData.getUsername());
        GotoGroupChatOutputData outputData = new GotoGroupChatOutputData(user.getName());
        GotoGroupChatOutPutBoundary.prepareSuccessView(outputData);
    }
}
