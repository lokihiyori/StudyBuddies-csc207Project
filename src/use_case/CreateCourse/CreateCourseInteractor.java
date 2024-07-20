package use_case.CreateCourse;

import entity.Course;
import entity.CourseFactory;
import entity.GroupChat;
import entity.GroupChatFactory;

public class CreateCourseInteractor implements CreateCourseInputBoundary {
    public CreateCourseOutputBoundary createCourseOutputBoundary;
    public CreateCourseDataAccessInterface createCourseDataAccessInterface;
    private final GroupChatFactory groupChatFactory;
    public CreateCourseInteractor(CreateCourseOutputBoundary createCourseOutputBoundary, CreateCourseDataAccessInterface createCourseDataAccessInterface, GroupChatFactory groupChatFactory){
        this.createCourseOutputBoundary = createCourseOutputBoundary;
        this.createCourseDataAccessInterface = createCourseDataAccessInterface;
        this.groupChatFactory = groupChatFactory;
    }

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
