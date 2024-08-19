package interface_adapter.CreateCourse;

import entity.GroupChat;
import use_case.CreateCourse.CreateCourseInputBoundary;
import use_case.CreateCourse.CreateCourseInputData;

/**
 * Controller class responsible for handling the creation of a course.
 * This class uses an input boundary to interact with the use case for course creation.
 */
public class CreateCourseController {
    private final CreateCourseInputBoundary createCourseInteractor;
    /**
     * Constructs a new CreateCourseController with the specified input boundary.
     *
     * @param createCourseInteractor the input boundary for creating a course
     */
    public CreateCourseController(CreateCourseInputBoundary createCourseInteractor) {
        this.createCourseInteractor = createCourseInteractor;
    }


    /**
     * Executes the course creation process with the specified name and code.
     * A new {@link GroupChat} is created with the course code and then used
     * to create a {@link CreateCourseInputData} object which is passed to
     * the input boundary to handle the creation logic.
     *
     * @param name the name of the course
     * @param code the code of the course
     */
    public void executeCreateCourse(String name, String code) {
        GroupChat groupChat = new GroupChat(code);
        CreateCourseInputData inputData = new CreateCourseInputData(name, code, groupChat);
        createCourseInteractor.execute(inputData);
    }
}
