package use_case;

import entity.Course;
import entity.GroupChat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.SearchCourse.CourseRepository;
import use_case.UserProfile.UserProfileInputData;
import use_case.UserProfile.UserProfileInteractor;
import use_case.UserProfile.UserProfileOutputBoundary;
import use_case.UserProfile.UserProfileOutputData;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class UserProfileInteractorTest {
    private UserProfileInteractor interactor;
    private UserProfileOutputBoundary outputBoundary;
    private CourseRepository courseRepository;
    private UserProfileInputData inputData;

    @BeforeEach
    void setUp() {
        outputBoundary = new UserProfileOutputBoundary() {
            @Override
            public UserProfileOutputData presentUserProfile(UserProfileOutputData outputData) {

                return outputData;
            }
        };
        courseRepository = new CourseRepository() {
            @Override
            public void addCourse(Course course) {

            }

            @Override
            public Course findCourseByCode(String courseId) {
                return null;
            }

            @Override
            public Course findCourseByName(String courseName) {
                return null;
            }
        };

        GroupChat groupChat1 = new GroupChat("CSC108");
        GroupChat groupChat2 = new GroupChat("CSC207");

        // Add some stub courses
        courseRepository.addCourse(new Course("Intro to programming","CSC108",groupChat1));
        courseRepository.addCourse(new Course("Software Design", "CSC207", groupChat2));

        interactor = new UserProfileInteractor(outputBoundary, courseRepository);

        LocalDateTime now = LocalDateTime.now();
        inputData = new UserProfileInputData(
                "testUser",
                "password123",
                "test@example.com",
                now,
                Arrays.asList("CSC108", "CSC207")
        );
    }

    @Test
    void testCreateUserProfile() {
        // Execute the use case
        interactor.createUserProfile(inputData);

        // Verify the results
        UserProfileOutputData expectedOutput = new UserProfileOutputData(
                "testUser",
                "test@example.com",
                inputData.getCreationTime().toString(),
                Arrays.asList("CSC108", "CSC207")
        );
        UserProfileOutputData actualOutput = outputBoundary.presentUserProfile(expectedOutput);

        assertEquals(expectedOutput.getName(), actualOutput.getName());
        assertEquals(expectedOutput.getEmail(), actualOutput.getEmail());
        assertEquals(expectedOutput.getCreationTime(), actualOutput.getCreationTime());
        assertEquals(expectedOutput.getCourseCodes(), actualOutput.getCourseCodes());
    }
}
