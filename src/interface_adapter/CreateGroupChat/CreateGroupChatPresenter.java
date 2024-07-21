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
        // On success, switch to the logged in view.
        CreateGroupChatState createGroupChatState = createGroupChatViewModel.getState();
        createGroupChatState.setCode(createGroupChatOutputData.getCode());
        this.createGroupChatViewModel.setState(createGroupChatState);
        this.createGroupChatViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {

    }
}
