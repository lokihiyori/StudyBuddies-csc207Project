package entity;

import java.util.List;

public interface User {

    String getName();

    String getUsername();

    String getPassword();

    String getEmail();

    List<Course> getCourses();

    User get(String users);
    List<GroupChat> getGroupChatList();
}

