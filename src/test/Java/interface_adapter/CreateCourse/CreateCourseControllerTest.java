package interface_adapter.CreateCourse;

import entity.GroupChat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import use_case.CreateCourse.CreateCourseInputBoundary;
import use_case.CreateCourse.CreateCourseInputData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

class CreateCourseControllerTest {

    private CreateCourseController controller;
    private CreateCourseInputBoundary createCourseInteractor;

    @BeforeEach
    void setUp() {
        createCourseInteractor = Mockito.mock(CreateCourseInputBoundary.class);
        controller = new CreateCourseController(createCourseInteractor);
    }

    @Test
    void executeCreateCourse() {
        // Arrange
        String name = "Test Course";
        String code = "TEST123";

        // Act
        controller.executeCreateCourse(name, code);

        // Assert
        ArgumentCaptor<CreateCourseInputData> captor = ArgumentCaptor.forClass(CreateCourseInputData.class);
        verify(createCourseInteractor).execute(captor.capture());

        CreateCourseInputData inputData = captor.getValue();
        assertEquals(name, inputData.getName());
        assertEquals(code, inputData.getCode());

        GroupChat groupChat = inputData.getGroupChat();
        assertEquals(code, groupChat.getCode());
    }
}
