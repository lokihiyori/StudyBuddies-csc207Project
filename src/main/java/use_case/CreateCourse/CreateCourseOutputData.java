package use_case.CreateCourse;

import entity.Course;
import entity.GroupChat;

/**
 * The CreateCourseOutputData class is a data structure that holds the information
 * related to the output of the course creation process. This includes the course name,
 * course code, associated group chat, and the course object itself.
 */
public class CreateCourseOutputData {
    private String name;
    private String code;
    private GroupChat groupChat;
    private Course course;

    /**
     * Constructs a new CreateCourseOutputData instance with the specified course name,
     * course code, associated group chat, and course object.
     *
     * @param name the name of the course.
     * @param code the code of the course.
     * @param groupChat the group chat associated with the course.
     * @param course the Course object created during the course creation process.
     */
    public CreateCourseOutputData(String name, String code, GroupChat groupChat, Course course) {
        this.name = name;
        this.code = code;
        this.groupChat = groupChat;
        this.course = course;
    }

    /**
     * Returns the Course object created during the course creation process.
     *
     * @return the Course object.
     */

    public Course getCourse() {
        return course;
    }

    /**
     * Sets the Course object.
     *
     * @param course the Course object to set.
     */
    public void setCourse(Course course) {
        this.course = course;
    }

    /**
     * Returns the name of the course.
     *
     * @return the course name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the code of the course.
     *
     * @return the course code.
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the code of the course.
     *
     * @param code the course code to set.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Returns the group chat associated with the course.
     *
     * @return the GroupChat object.
     */
    public GroupChat getGroupChat() {
        return groupChat;
    }

    /**
     * Sets the name of the course.
     *
     * @param name the course name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the group chat associated with the course.
     *
     * @param groupChat the GroupChat object to set.
     */
    public void setGroupChat(GroupChat groupChat) {
        this.groupChat = groupChat;
    }
}
