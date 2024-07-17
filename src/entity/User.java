package entity;

import java.util.List;

public interface User {

    String getName();

    String getUsername();

    String getPassword();

    String getEmail();

    List<String> getCourses();

    User get(String users);
    List<GroupChat> getGroupChatList();
}

