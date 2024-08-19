package interface_adapter.CreateCourse;
/**
 * Represents the state of a course during the course creation process.
 * This class holds the course's name and code, which can be used to
 * update the view model with the current state of the course.
 */
public class CreateCourseState {
    private String code;
    private String name;
    /**
     * Constructs a new {@link CreateCourseState} with the specified name and code.
     *
     * @param name the name of the course
     * @param code the code of the course
     */
    public CreateCourseState(String name, String code) {
        this.name = name;
        this.code = code;
    }
    /**
     * Default constructor for {@link CreateCourseState}.
     * Initializes an empty state.
     */
    public CreateCourseState(){}

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
     * Sets the code of the course.
     *
     * @param code the new code of the course
     */
    public void setCode(String code) {
        this.code = code;
    }


    /**
     * Returns the code of the course.
     *
     * @return the code of the course
     */
    public String getCode() {
        return code;
    }
}
