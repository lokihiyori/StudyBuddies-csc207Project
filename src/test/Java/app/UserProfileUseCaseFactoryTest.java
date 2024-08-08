package app;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import data_access.CourseDataAccessObject;
import data_access.FileUserDataAccessObject;
import entity.*;
import interface_adapter.UserProfile.UserProfileController;
import interface_adapter.UserProfile.UserProfilePresenter;
import interface_adapter.UserProfile.UserProfileState;
import interface_adapter.UserProfile.UserProfileViewModel;
import use_case.UserProfile.UserProfileInteractor;
import use_case.UserProfile.UserProfileOutputBoundary;
import view.UserprofileView;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserProfileUseCaseFactoryTest {

    private FileUserDataAccessObject userDataAccessObject;
    private CourseDataAccessObject courseDataAccessObject;
    private User user;

    @BeforeEach
    public void setUp() {
        userDataAccessObject = mock(FileUserDataAccessObject.class);
        courseDataAccessObject = mock(CourseDataAccessObject.class);
        user = new CommonUser("testUser", "test@example.com", "password", LocalDateTime.now());

        when(userDataAccessObject.get("testUser")).thenReturn(user);
    }

    @Test
    public void testCreateUserProfileView() throws IOException {
        // Mock course data
        Map<String, Course> courseMap = new HashMap<>();
        GroupChatFactory groupChatFactory = new GroupChatFactory();
        GroupChat groupChat = groupChatFactory.create("COURSE_CODE");
        Course course = new Course("Course Name", "COURSE_CODE", groupChat);
        courseMap.put(course.getCode(), course);
        when(courseDataAccessObject.getCourseMap()).thenReturn(courseMap);

        // Create the UserprofileView using the factory
        UserprofileView userProfileView = UserProfileUseCaseFactory.create(
                userDataAccessObject,
                courseDataAccessObject,
                "testUser"
        );

        // Verify that the UserProfileView was created and initialized correctly
        assertNotNull(userProfileView);

        // Verify UserProfileController initialization
        UserProfileController controller = userProfileView.getController();
        assertNotNull(controller);

        // Verify the UserProfileState
        UserProfileState state = userProfileView.getState();
        assertEquals("testUser", state.getName());
        assertEquals("test@example.com", state.getEmail());
        assertNotNull(state.getCreationTime());
        assertTrue(state.getCourseCodes().contains("COURSE_CODE"));
    }

    @Test
    public void testCreateUserProfileViewUserNotFound() {
        // Configure mock to return null for user
        when(userDataAccessObject.get("nonExistentUser")).thenReturn(null);

        // Assert that creating a UserprofileView throws RuntimeException
        assertThrows(RuntimeException.class, () -> UserProfileUseCaseFactory.create(
                userDataAccessObject,
                courseDataAccessObject,
                "nonExistentUser"
        ));
    }
}
