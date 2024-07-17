package usecase.CreateGroupChat;

import entity.User;

public class CreateGroupChatInteractor implements CreateGroupChatInputBoundary{
    final CreateGroupChatOutputBoundary createGroupChatPresenter;
    final CreateGroupChatDataAccessInterface groupchatDataAccessObject;
    public CreateGroupChatInteractor(CreateGroupChatOutputBoundary createGroupChatPresenter, CreateGroupChatDataAccessInterface groupchatDataAccessObject){
        this.createGroupChatPresenter =  createGroupChatPresenter;
        this.groupchatDataAccessObject = groupchatDataAccessObject;
    }

    @Override
    public void execute(CreateGroupChatInputData createGroupChatInputData) {
        String code = createGroupChatInputData.getCode();
        CreateGroupChatOutputData createGroupChatOutputData = new CreateGroupChatOutputData(code);
        createGroupChatPresenter.prepareSuccessView(createGroupChatOutputData);
    }
}
