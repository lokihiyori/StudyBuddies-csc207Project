package app;

import interface_adapter.LeaveGroupChat.LeaveGroupChatController;
import interface_adapter.LeaveGroupChat.LeaveGroupChatPresenter;
import interface_adapter.LeaveGroupChat.LeaveGroupChatViewModel;
import use_case.LeaveGroupChat.LeaveGroupChatDataAccessInterface;
import use_case.LeaveGroupChat.LeaveGroupChatInputBoundary;
import use_case.LeaveGroupChat.LeaveGroupChatInteractor;

public class LeaveGroupChatMain {
    public static void main(String[] args) {
        LeaveGroupChatDataAccessInterface dataAccess = new GroupChatDataAccess();

        LeaveGroupChatViewModel viewModel = new LeaveGroupChatViewModel();

        LeaveGroupChatPresenter presenter = new LeaveGroupChatPresenter();

        LeaveGroupChatInputBoundary interactor = new LeaveGroupChatInteractor(dataAccess, presenter);

        LeaveGroupChatController controller = new LeaveGroupChatController(interactor);

        String userId = "John";
        String chatId = "CSC207";

        viewModel.updateLoadingState(true);
        controller.handleLeaveRequest(userId, chatId);
    }
}

