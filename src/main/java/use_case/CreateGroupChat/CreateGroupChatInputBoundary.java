package use_case.CreateGroupChat;

import java.io.IOException;

public interface CreateGroupChatInputBoundary {
    void execute(CreateGroupChatInputData createGroupChatInputData) throws IOException;
}
