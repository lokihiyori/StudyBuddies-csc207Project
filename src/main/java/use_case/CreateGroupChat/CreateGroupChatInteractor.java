package use_case.CreateGroupChat;

import entity.GroupChat;
import entity.GroupChatFactory;

import java.io.IOException;

/**
 * The CreateGroupChatInteractor class implements the CreateGroupChatInputBoundary interface
 * and handles the business logic for creating a new group chat. It interacts with the
 * data access layer, uses a factory to create the group chat, and communicates with the
 * output boundary to present the result of the group chat creation process.
 */
public class CreateGroupChatInteractor implements CreateGroupChatInputBoundary {
    final CreateGroupChatOutputBoundary createGroupChatPresenter;
    final CreateGroupChatDataAccessInterface groupChatDataAccessObject;
    final GroupChatFactory groupChatFactory;

    /**
     * Constructs a new CreateGroupChatInteractor with the specified output boundary,
     * data access interface, and group chat factory.
     *
     * @param createGroupChatPresenter the output boundary to handle the result of the group chat creation process.
     * @param groupchatDataAccessObject the data access interface to interact with the data storage system.
     * @param groupChatFactory the factory used to create a group chat.
     */
    public CreateGroupChatInteractor(CreateGroupChatOutputBoundary createGroupChatPresenter, CreateGroupChatDataAccessInterface groupchatDataAccessObject, GroupChatFactory groupChatFactory){
        this.createGroupChatPresenter =  createGroupChatPresenter;
        this.groupChatDataAccessObject = groupchatDataAccessObject;
        this.groupChatFactory = groupChatFactory;
    }

    /**
     * Executes the group chat creation process using the provided input data.
     * The process involves checking if a group chat with the same code already exists,
     * creating the group chat using the factory, saving it to the data storage system,
     * and preparing the output view based on the result.
     *
     * @param createGroupChatInputData the data required to create a new group chat.
     * @throws IOException if there is an error during the group chat creation process.
     */
    @Override
    public void execute(CreateGroupChatInputData createGroupChatInputData) throws IOException {
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
