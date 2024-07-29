package UseCaseInteractorsTest;

import entity.GroupChat;
import entity.GroupChatFactory;
import interface_adapter.CreateGroupChat.CreateGroupChatPresenter;
import interface_adapter.CreateGroupChat.CreateGroupChatViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.CreateGroupChat.CreateGroupChatDataAccessInterface;
import use_case.CreateGroupChat.CreateGroupChatInputData;
import use_case.CreateGroupChat.CreateGroupChatInteractor;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CreateGroupChat_test {
    private CreateGroupChatInteractor interactor;
    private CreateGroupChatViewModel viewModel;
    private CreateGroupChatPresenter presenter;
    private InMemoryGroupChatDataAccessObject dataAccessObject;
    private GroupChatFactory groupChatFactory;

    @BeforeEach
    void setUp() {
        viewModel = new CreateGroupChatViewModel();
        presenter = new CreateGroupChatPresenter(viewModel);
        dataAccessObject = new InMemoryGroupChatDataAccessObject();
        groupChatFactory = new GroupChatFactory();
        interactor = new CreateGroupChatInteractor(presenter, dataAccessObject, groupChatFactory);
    }

    @Test
    void testCreateGroupChatSuccess() {
        CreateGroupChatInputData inputData = new CreateGroupChatInputData("CSC207");
        interactor.execute(inputData);

        assertEquals("CSC207", viewModel.getState().getCode());
    }

    @Test
    void testCreateGroupChatAlreadyExists() {
        // First create a group chat
        dataAccessObject.saveGroupChat(new GroupChat("CSC207"));

        // Try to create the same group chat again
        CreateGroupChatInputData inputData = new CreateGroupChatInputData("CSC207");
        interactor.execute(inputData);

        // Check if the error was handled
        assertTrue(viewModel.getState().getCode() == null);
    }

    private static class InMemoryGroupChatDataAccessObject implements CreateGroupChatDataAccessInterface {
        private final Map<String, GroupChat> groupChats = new HashMap<>();

        @Override
        public void saveGroupChat(GroupChat groupChat) {
            groupChats.put(groupChat.getCode(), groupChat);
        }

        @Override
        public GroupChat getGroupChat(String code) {
            return null;
        }

        @Override
        public boolean existsByCode(String code) {
            return groupChats.containsKey(code);
        }
    }
}
