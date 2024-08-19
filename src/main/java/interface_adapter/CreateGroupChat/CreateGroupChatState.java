package interface_adapter.CreateGroupChat;

import entity.CommonUser;

import java.util.List;

/**
 * Represents the state of a group chat creation process.
 * This class holds the details about the group chat being created,
 * including its code and the list of users associated with the chat.
 */
public class CreateGroupChatState {
    private String code;
    private List<CommonUser> usersList;
    /**
     * Constructs a new CreateGroupChatState as a copy of the provided state.
     *
     * @param copy the CreateGroupChatState instance to copy from
     */
    public CreateGroupChatState(CreateGroupChatState copy){
        code =copy.code;
        usersList = copy.usersList;
    }
    /**
     * Constructs a new, empty CreateGroupChatState.
     */
    public CreateGroupChatState(){}
    /**
     * Returns the code of the group chat.
     *
     * @return the code of the group chat
     */
    public String getCode() {
        return code;
    }
    /**
     * Sets the code of the group chat.
     *
     * @param code the new code for the group chat
     */
    public void setCode(String code) {
        this.code = code;
    }
    /**
     * Returns the list of users associated with the group chat.
     *
     * @return the list of users in the group chat
     */

    public List<CommonUser> getUsersList() {
        return usersList;
    }

    /**
     * Sets the list of users associated with the group chat.
     *
     * @param usersList the new list of users for the group chat
     */

    public void setUsersList(List<CommonUser> usersList) {
        this.usersList = usersList;
    }
}




