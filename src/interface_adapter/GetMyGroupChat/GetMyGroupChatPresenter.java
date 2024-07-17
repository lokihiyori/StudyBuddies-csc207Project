package interface_adapter.GetMyGroupChat;

import usecase.GetMyGroupChat.GetMyGroupChatOutputData;

public class GetMyGroupChatPresenter {
    private final GetMyGroupChatViewModel getMyGroupChatViewModel;
    public GetMyGroupChatPresenter(GetMyGroupChatViewModel getMyGroupChatViewModel){
        this.getMyGroupChatViewModel = getMyGroupChatViewModel;
    }


    public void prepareSuccessView(GetMyGroupChatOutputData getMyGroupChatOutputData) {
        GetMyGroupChatState getMyGroupChatState = getMyGroupChatViewModel.getState();
        getMyGroupChatState.setUsers(getMyGroupChatOutputData.getUsers());
        this.getMyGroupChatViewModel.setState(getMyGroupChatState);
        this.getMyGroupChatViewModel.firePropertyChanged();
    }
}
