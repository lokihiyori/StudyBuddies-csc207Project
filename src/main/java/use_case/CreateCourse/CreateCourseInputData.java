package use_case.CreateCourse;

import entity.GroupChat;

/**
 * The CreateCourseInputData class is a data structure that holds the necessary
 * information required to create a new course. This includes the course code,
 * course name, and associated group chat.
 */
public class CreateCourseInputData {
    final private String code;
    final private String name;
    final private GroupChat groupChat;

    /**
     * Constructs a new CreateCourseInputData instance with the specified course name,
     * course code, and associated group chat.
     *
     * @param name the name of the course.
     * @param code the unique code of the course.
     * @param groupChat the group chat associated with the course.
     */
    public CreateCourseInputData(String name, String code, GroupChat groupChat) {
        this.code = code;
        this.name = name;
        this.groupChat = groupChat;
    }

    /**
     * Returns the course code.
     *
     * @return the code of the course.
     */
    public String getCode() {
        return code;
    }

    /**
     * Returns the course name.
     *
     * @return the name of the course.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the group chat associated with the course.
     *
     * @return the GroupChat instance associated with the course.
     */
    public GroupChat getGroupChat() {
        return groupChat;
    }
}
