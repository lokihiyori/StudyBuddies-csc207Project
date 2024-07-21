package entity;

public class CourseFactory {

    public static Course create(String name, String code, GroupChat groupChat) {
        return new Course(name, code, groupChat);
    }
}
