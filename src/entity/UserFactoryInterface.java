package entity;

import java.time.LocalDateTime;
import java.util.List;

public interface UserFactoryInterface {
    /** Requires: password is valid. */
    User createUser(String name, String username, String password, String email, List<String> courses, List<GroupChat> groupChatList);
}
