package interface_adapter.CreateGroupChat;

import use_case.CreateGroupChat.CreateGroupChatOutputBoundary;
import use_case.CreateGroupChat.CreateGroupChatOutputData;

/**
 * Presenter for handling the output of group chat creation operations.
 * It prepares the view model with the appropriate state based on the result
 * of the group chat creation process.
 */
public class CreateGroupChatPresenter implements CreateGroupChatOutputBoundary {
    private final CreateGroupChatViewModel createGroupChatViewModel;
    /**
     * Constructs a new CreateGroupChatPresenter with the specified view model.
     *
     * @param createGroupChatViewModel the view model for the group chat creation view
     */
    public CreateGroupChatPresenter(CreateGroupChatViewModel createGroupChatViewModel){
        this.createGroupChatViewModel = createGroupChatViewModel;
    }
    /**
     * Prepares the view model for a successful group chat creation.
     * Updates the view model's state with the data from the output data.
     *
     * @param createGroupChatOutputData the output data containing the group chat details
     */
    @Override
    public void prepareSuccessView(CreateGroupChatOutputData createGroupChatOutputData) {
        CreateGroupChatState state = new CreateGroupChatState();
        state.setCode(createGroupChatOutputData.getCode());
        createGroupChatViewModel.setState(state);
    }

    /**
     * Prepares the view model for a failed group chat creation.
     * This method currently does not handle failure scenarios.
     *
     * @param error a string describing the error
     */
    @Override
    public void prepareFailView(String error) {

    }
}
