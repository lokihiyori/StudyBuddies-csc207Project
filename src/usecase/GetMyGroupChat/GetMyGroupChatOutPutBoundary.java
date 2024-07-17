package usecase.GotoGroupChat;

public interface GotoGroupChatOutPutBoundary {
    static void prepareSuccessView(GotoGroupChatOutputData outputData) {
    }

    void execute(GotoGroupChatOutputData gotoGroupChatOutPutData);
}
