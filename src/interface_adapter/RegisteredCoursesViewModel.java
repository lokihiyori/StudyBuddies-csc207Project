package interface_adapter;

import entity.CommonUser;
import java.util.List;
import java.util.stream.Collectors;

public class RegisteredCoursesViewModel {
    private final CommonUser user;

    public RegisteredCoursesViewModel(CommonUser user) {
        this.user = user;
    }

    public String getFormattedGroupChatCodes() {
        List<String> codes = user.getGroupChatList().stream()
                .map(groupChat -> groupChat.getCode())
                .collect(Collectors.toList());
        return String.join("\n", codes);
    }
}
