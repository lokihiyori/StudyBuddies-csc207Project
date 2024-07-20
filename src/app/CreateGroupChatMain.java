package app;

import data_access.GroupChatDataAccessObject;
import entity.GroupChatFactory;
import interface_adapter.CreateGroupChat.*;
import usecase.CreateGroupChat.*;


public class CreateGroupChatMain {
    public CreateGroupChatMain(String[] args){
        CreateGroupChatViewModel viewModel = new CreateGroupChatViewModel();
        CreateGroupChatPresenter presenter = new CreateGroupChatPresenter(viewModel);
        CreateGroupChatDataAccessInterface dataAccessObject = new GroupChatDataAccessObject();
        GroupChatFactory groupChatFactory = new GroupChatFactory();

        CreateGroupChatInteractor interactor = new CreateGroupChatInteractor(presenter, dataAccessObject, groupChatFactory);

        CreateGroupChatController controller = new CreateGroupChatController(interactor);

        controller.executeCreateGroupChat("CSC207");
    }
}
