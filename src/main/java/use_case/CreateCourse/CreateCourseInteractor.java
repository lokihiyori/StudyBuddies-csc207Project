package use_case.CreateCourse;

import entity.Course;
import entity.CourseFactory;
import entity.GroupChat;
import entity.GroupChatFactory;

/**
 * The CreateCourseInteractor class implements the CreateCourseInputBoundary interface
 * and handles the business logic for creating a new course. It interacts with the
 * data access layer, creates the necessary group chat, and communicates with the
 * output boundary to present the result.
 */
public class CreateCourseInteractor implements CreateCourseInputBoundary {
    public CreateCourseOutputBoundary createCourseOutputBoundary;
    public CreateCourseDataAccessInterface createCourseDataAccessInterface;
    private final GroupChatFactory groupChatFactory;

    /**
     * Constructs a new CreateCourseInteractor with the specified output boundary,
     * data access interface, and group chat factory.
     *
     * @param createCourseOutputBoundary the output boundary to handle the result of the course creation process.
     * @param createCourseDataAccessInterface the data access interface to interact with the data storage system.
     * @param groupChatFactory the factory used to create a group chat for the course.
     */
    public CreateCourseInteractor(CreateCourseOutputBoundary createCourseOutputBoundary, CreateCourseDataAccessInterface createCourseDataAccessInterface, GroupChatFactory groupChatFactory){
        this.createCourseOutputBoundary = createCourseOutputBoundary;
        this.createCourseDataAccessInterface = createCourseDataAccessInterface;
        this.groupChatFactory = groupChatFactory;
    }

    /**
     * Executes the course creation process using the provided input data.
     * The process includes checking if a course with the same code already exists,
     * creating a group chat, creating the course, saving it to the data storage system,
     * and preparing the output view based on the result.
     *
     * @param inputData the data required to create a new course.
     */
    @Override
    public void execute(CreateCourseInputData inputData) {
        // Check if a course with the same code already exists
        if (createCourseDataAccessInterface.existsByCode(inputData.getCode())) {
            createCourseOutputBoundary.prepareFailView("Course already exists.");
            return;
        }

        // Create Group Chat
        GroupChat groupChat = groupChatFactory.create(inputData.getCode());

        // Create Course
        Course course = CourseFactory.create(inputData.getName(), inputData.getCode(), groupChat);
        createCourseDataAccessInterface.saveCourse(course);

        // Prepare Output Data
        CreateCourseOutputData outputData = new CreateCourseOutputData(course.getName(), course.getCode(), groupChat, course);
        createCourseOutputBoundary.prepareSuccessView(outputData);
    }

}
