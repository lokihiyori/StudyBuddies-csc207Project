package interface_adapter.CreateGroupChat;

import use_case.CreateGroupChat.CreateGroupChatOutputBoundary;
import use_case.CreateGroupChat.CreateGroupChatOutputData;

public class CreateGroupChatPresenter implements CreateGroupChatOutputBoundary {
    private final CreateGroupChatViewModel createGroupChatViewModel;
    public CreateGroupChatPresenter(CreateGroupChatViewModel createGroupChatViewModel){
        this.createGroupChatViewModel = createGroupChatViewModel;
    }
    @Override
    public void prepareSuccessView(CreateGroupChatOutputData createGroupChatOutputData) {
        CreateGroupChatState state = new CreateGroupChatState();
        state.setCode(createGroupChatOutputData.getCode());
        createGroupChatViewModel.setState(state);
    }

    @Override
    public void prepareFailView(String error) {

    }
}
