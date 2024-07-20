package app;

import data_access.GroupChatDataAccessObject;
import entity.GroupChatFactory;
import interface_adapter.CreateGroupChat.CreateGroupChatController;
import interface_adapter.CreateGroupChat.CreateGroupChatPresenter;
import interface_adapter.CreateGroupChat.CreateGroupChatViewModel;
import use_case.CreateGroupChat.CreateGroupChatDataAccessInterface;
import use_case.CreateGroupChat.CreateGroupChatInteractor;
import view.CreateGroupChatView;

public class CreateGroupChatMain {
    public static void main(String[] args) {
        // Initialize ViewModel, Controller, and View for demonstration
        CreateGroupChatViewModel viewModel = new CreateGroupChatViewModel();
        CreateGroupChatPresenter presenter = new CreateGroupChatPresenter(viewModel);
        CreateGroupChatDataAccessInterface dataAccess = new GroupChatDataAccessObject();
        GroupChatFactory factory = new GroupChatFactory();
        CreateGroupChatInteractor interactor = new CreateGroupChatInteractor(presenter, dataAccess, factory);
        CreateGroupChatController controller = new CreateGroupChatController(interactor);
        CreateGroupChatView view = new CreateGroupChatView(viewModel, controller);

        view.setVisible(true);
    }

}
