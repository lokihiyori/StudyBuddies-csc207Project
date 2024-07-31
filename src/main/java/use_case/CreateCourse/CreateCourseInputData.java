package use_case.CreateCourse;

import entity.GroupChat;

public class CreateCourseInputData {
    final private String code;
    final private String name;
    final private GroupChat groupChat;

    public CreateCourseInputData(String name, String code, GroupChat groupChat) {
        this.code = code;
        this.name = name;
        this.groupChat = groupChat;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public GroupChat getGroupChat() {
        return groupChat;
    }
}
