package entity;

import java.util.List;

public class Users implements User{
    private final String name;
    private final String username;
    private final String password;
    private final String email;
    private final List<String> courses;
    private final List<GroupChat> groupChatList;


    Users(String name, String password, String username, String email, List<String> courses, List<GroupChat> groupChatList) {
        this.name = name;
        this.password = password;
        this.username = username;
        this.email = email;
        this.courses = courses;
        this.groupChatList = groupChatList;
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getUsername() {
        return username;
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
}

