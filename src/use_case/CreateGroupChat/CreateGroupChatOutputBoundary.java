package usecase.CreateGroupChat;

public interface CreateGroupChatOutputBoundary {
    void prepareSuccessView(CreateGroupChatOutputData createGroupChatOutputData);

    void prepareFailView(String error);
}
