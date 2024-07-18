package usecase.SignUp;

import entity.GroupChat;

import java.util.List;

public class SignUpInputData {
    final private String name;
    final private String username;
    final private String password;
    final private String repeatPassword;
    final private String email;
    final private List<String> courses;
    final private List<GroupChat> groupChatList;

    public SignUpInputData(String name, String username, String password, String repeatPassword, String email, List<String> courses, List<GroupChat> groupChatList) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.email = email;
        this.courses = courses;
        this.groupChatList = groupChatList;
    }

    String getName() {
        return name;
    }

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    String getEmail() {
        return email;
    }

    public List<String> getCourses() {
        return courses;
    }

    public List<GroupChat> getGroupChatList() {
        return groupChatList;
    }
}
