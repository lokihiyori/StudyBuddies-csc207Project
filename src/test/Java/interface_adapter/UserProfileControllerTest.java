package interface_adapter;

import interface_adapter.UserProfile.UserProfileController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import use_case.UserProfile.UserProfileInputData;
import use_case.UserProfile.UserProfileInteractor;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserProfileControllerTest {

    private UserProfileInteractor mockInteractor;
    private UserProfileController controller;

    @BeforeEach
    void setUp() {
        mockInteractor = mock(UserProfileInteractor.class);
        controller = new UserProfileController(mockInteractor);
    }

    @Test
    void testCreateUserProfile() {
        // Arrange
        String name = "John Doe";
        String password = "password123";
        String email = "john.doe@example.com";
        LocalDateTime creationTime = LocalDateTime.of(2023, 1, 1, 0, 0);
        List<String> courseCodes = Arrays.asList("CSC101", "CSC102");

        // Act
        controller.createUserProfile(name, password, email, creationTime, courseCodes);

        // Assert
        ArgumentCaptor<UserProfileInputData> argumentCaptor = ArgumentCaptor.forClass(UserProfileInputData.class);
        verify(mockInteractor).createUserProfile(argumentCaptor.capture());
        UserProfileInputData capturedInputData = argumentCaptor.getValue();

        assertEquals(name, capturedInputData.getName());
        assertEquals(password, capturedInputData.getPassword());
        assertEquals(email, capturedInputData.getEmail());
        assertEquals(creationTime, capturedInputData.getCreationTime());
        assertEquals(courseCodes, capturedInputData.getCourseCodes());
    }
}

