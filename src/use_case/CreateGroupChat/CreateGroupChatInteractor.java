package use_case.CreateGroupChat;

import entity.GroupChat;
import entity.GroupChatFactory;

public class CreateGroupChatInteractor implements CreateGroupChatInputBoundary {
    final CreateGroupChatOutputBoundary createGroupChatPresenter;
    final CreateGroupChatDataAccessInterface groupChatDataAccessObject;
    final GroupChatFactory groupChatFactory;
    public CreateGroupChatInteractor(CreateGroupChatOutputBoundary createGroupChatPresenter, CreateGroupChatDataAccessInterface groupchatDataAccessObject, GroupChatFactory groupChatFactory){
        this.createGroupChatPresenter =  createGroupChatPresenter;
        this.groupChatDataAccessObject = groupchatDataAccessObject;
        this.groupChatFactory = groupChatFactory;
    }

    @Override
    public void execute(CreateGroupChatInputData createGroupChatInputData) {
        if (groupChatDataAccessObject.existsByCode(createGroupChatInputData.getCode())) {
            createGroupChatPresenter.prepareFailView("GroupChat already exists.");
        }
        else {
            GroupChat groupChat = groupChatFactory.create(createGroupChatInputData.getCode());
            groupChatDataAccessObject.saveGroupChat(groupChat);

            String code = groupChat.getCode();
            CreateGroupChatOutputData createGroupChatOutputData = new CreateGroupChatOutputData(code, groupChat);
            createGroupChatPresenter.prepareSuccessView(createGroupChatOutputData);
        }
    }
}
