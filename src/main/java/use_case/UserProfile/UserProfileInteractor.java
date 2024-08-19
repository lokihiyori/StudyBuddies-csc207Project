package use_case.UserProfile;

import entity.CommonUser;
import entity.Course;
import entity.User;
import use_case.SearchCourse.CourseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The interactor responsible for creating user profiles.
 */
public class UserProfileInteractor implements UserProfileInputBoundary {
    private final UserProfileOutputBoundary outputBoundary;
    private final CourseRepository courseRepository;

    /**
     * Constructs a UserProfileInteractor with the specified output boundary and course repository.
     *
     * @param outputBoundary the boundary responsible for presenting the user profile data.
     * @param courseRepository the repository for retrieving course information.
     */
    public UserProfileInteractor(UserProfileOutputBoundary outputBoundary, CourseRepository courseRepository) {
        this.outputBoundary = outputBoundary;
        this.courseRepository = courseRepository;
    }

    /**
     * Creates a user profile using the provided input data and presents the result.
     *
     * @param inputData the data required to create the user profile.
     */
    @Override
    public void createUserProfile(UserProfileInputData inputData) {
        User user = new CommonUser(inputData.getName(), inputData.getPassword(), inputData.getEmail(), inputData.getCreationTime());
        List<Course> courses = new ArrayList<>();
        for (String courseCode : inputData.getCourseCodes()) {
            Course course = courseRepository.findCourseByCode(courseCode);
            if (course != null) {
                courses.add(course);
            }
        }


        UserProfileOutputData outputData = new UserProfileOutputData(
                user.getName(),
                user.getEmail(),
                user.getCreationTime().toString(),
                courses.stream().map(Course::getCode).collect(Collectors.toList())
        );
        outputBoundary.presentUserProfile(outputData);
    }
}
