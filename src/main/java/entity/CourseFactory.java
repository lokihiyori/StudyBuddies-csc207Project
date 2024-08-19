package entity;
/**
 * A factory class for creating instances of {@link Course}.
 */
public class CourseFactory {

    /**
     * Creates a new {@link Course} with the specified name, code, and group chat.
     *
     * @param name      the name of the course
     * @param code      the code of the course
     * @param groupChat the group chat associated with the course
     * @return          a new instance of {@link Course}
     */
    public static Course create(String name, String code, GroupChat groupChat) {
        return new Course(name, code, groupChat);
    }
}
