package usecase.CreateCourse;

import entity.Course;
import entity.GroupChat;

public class CreateCourseOutputData {
    private String name;
    private String code;
    private GroupChat groupChat;
    private Course course;

    public CreateCourseOutputData(String name, String code, GroupChat groupChat, Course course) {
        this.name = name;
        this.code = code;
        this.groupChat = groupChat;
        this.course = course;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public GroupChat getGroupChat() {
        return groupChat;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGroupChat(GroupChat groupChat) {
        this.groupChat = groupChat;
    }
}
