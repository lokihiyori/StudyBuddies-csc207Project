package entity;

import java.time.LocalDateTime;
import java.util.List;

public interface User {

    String getName();


    LocalDateTime getCreationTime();

    String getPassword();

    String getEmail();

    List<String> getCourses();

    User get(String users);
    List<GroupChat> getGroupChatList();

}

