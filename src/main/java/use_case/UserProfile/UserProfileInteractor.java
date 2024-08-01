package use_case.UserProfile;

import entity.CommonUser;
import entity.Course;
import entity.User;
import use_case.SearchCourse.CourseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserProfileInteractor implements UserProfileInputBoundary {
    private final UserProfileOutputBoundary outputBoundary;
    private final CourseRepository courseRepository;

    public UserProfileInteractor(UserProfileOutputBoundary outputBoundary, CourseRepository courseRepository) {
        this.outputBoundary = outputBoundary;
        this.courseRepository = courseRepository;
    }

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
