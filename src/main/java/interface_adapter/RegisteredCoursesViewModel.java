package interface_adapter;

import entity.CommonUser;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ViewModel for managing and displaying the list of registered courses and group chats for a user.
 */
public class RegisteredCoursesViewModel {
    private final CommonUser user;

    /**
     * Constructs a RegisteredCoursesViewModel with the specified user.
     *
     * @param user the CommonUser instance representing the user whose courses and group chats are managed
     */
    public RegisteredCoursesViewModel(CommonUser user) {
        this.user = user;
    }

    /**
     * Returns a formatted string of group chat codes that the user is registered in.
     * Each code is separated by a newline character.
     *
     * @return a string containing the group chat codes, each on a new line
     */
    public String getFormattedGroupChatCodes() {
        List<String> codes = user.getGroupChatList().stream()
                .map(groupChat -> groupChat.getCode())
                .collect(Collectors.toList());
        return String.join("\n", codes);
    }
}
