package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CommonUser implements User{
    private final String name;
    private final LocalDateTime creationTime;
    private final String password;
    private final String email;
    private final List<String> courses;
    private final List<GroupChat> groupChatList;

    public CommonUser(String name, String password, LocalDateTime creationTime) {
        this.name = name;
        this.password = password;
        this.creationTime = creationTime;
        this.email = "";
        this.courses = new ArrayList<>();
        this.groupChatList = new ArrayList<>();;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public List<String> getCourses() {
        return courses;
    }

    @Override
    public User get(String users) {
        return null;
    }

    @Override
    public List<GroupChat> getGroupChatList() {
        return groupChatList;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }
}

