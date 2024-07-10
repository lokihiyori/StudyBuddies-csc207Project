package entity;

/**
 * The Course class represents a course with a name, code, and group chat.
 */
public class Course {
    // Attributes
    // The types of attributes need to be discussed later!!!!!!!!
    private String name;
    private String code;
    private GroupChat groupchat;

    /**
     * Constructs a new Course with the specified name, code, and group chat.
     *
     * @param name      the name of the course
     * @param code      the code of the course
     * @param groupchat the group chat identifier for the course
     */
    public Course(String name, String code, GroupChat groupchat) {
        this.name = name;
        this.code = code;
        this.groupchat = groupchat;
    }


    //Getter and Setter allow access and modification of the private attributes
    /**
     * Returns the name of the course.
     *
     * @return the name of the course
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the course.
     *
     * @param name the new name of the course
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the code of the course.
     *
     * @return the code of the course
     */
    public String getCode() {
        return code;
    }


    /**
     * Sets the code of the course.
     *
     * @param code the new code of the course
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Returns the group chat identifier of the course.
     *
     * @return the group chat identifier of the course
     */
    public GroupChat getGroupchat() {
        return groupchat;
    }

    /**
     * Sets the group chat identifier of the course.
     *
     * @param groupchat the new group chat identifier of the course
     */
    public void setGroupchat(GroupChat groupchat) {
        this.groupchat = groupchat;
    }

    /**
     * Returns a string representation of the course.
     *
     * @return a string representation of the course
     */
    @Override
    public String toString() {
        // This method overrides the toString() which belongs to the Object class. It is used to form
        // a proper string which contains all the attributes' information of the Course object.
        return "Course name: " + name + ", Course code: " + code + ", Groupchat: " + groupchat;
    }


    /**
     * The main method demonstrates creating an instance of the Course class and printing its details.
     *
     * @param args command-line arguments (not used)
     */
    /*
    public static void main(String[] args) {
        // Creating an object of Course
        //need to pass in code for creating GroupChat!!!!!!!!!!!!!!!!!!!!!!!!!
        GroupChat chat = new GroupChat("CSC207");
        Course course = new Course("Software Design", "CSC207", chat);

        // Printing the course details
        System.out.println(course.toString());
    }
     */
}
