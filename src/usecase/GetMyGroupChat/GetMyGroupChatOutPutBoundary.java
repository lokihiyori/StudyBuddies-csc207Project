package usecase.GetMyGroupChat;

public interface GetMyGroupChatOutPutBoundary {
    static void prepareSuccessView(GetMyGroupChatOutputData outputData) {
    }

    void execute(GetMyGroupChatOutputData getMyGroupChatOutPutData);
}
