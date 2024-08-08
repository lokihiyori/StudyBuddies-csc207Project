package use_case;

import entity.Course;
import entity.CourseFactory;
import entity.GroupChat;
import entity.GroupChatFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import use_case.CreateCourse.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateCourseInteractorTest {

    private CreateCourseOutputBoundary mockOutputBoundary;
    private CreateCourseDataAccessInterface mockDataAccessInterface;
    private GroupChatFactory mockGroupChatFactory;
    private CreateCourseInteractor interactor;

    @BeforeEach
    void setUp() {
        mockOutputBoundary = Mockito.mock(CreateCourseOutputBoundary.class);
        mockDataAccessInterface = Mockito.mock(CreateCourseDataAccessInterface.class);
        mockGroupChatFactory = Mockito.mock(GroupChatFactory.class);
        interactor = new CreateCourseInteractor(mockOutputBoundary, mockDataAccessInterface, mockGroupChatFactory);
    }

    @Test
    void testExecute_CourseAlreadyExists() {
        String courseName = "Software Engineering";
        String courseCode = "CSC207";
        GroupChat mockGroupChat = Mockito.mock(GroupChat.class);
        CreateCourseInputData inputData = new CreateCourseInputData(courseName, courseCode, mockGroupChat);

        when(mockDataAccessInterface.existsByCode(courseCode)).thenReturn(true);

        interactor.execute(inputData);

        verify(mockOutputBoundary).prepareFailView("Course already exists.");
        verify(mockDataAccessInterface, never()).saveCourse(any(Course.class));
    }

    @Test
    void testExecute_CourseCreationSuccess() {
        String courseName = "Software Engineering";
        String courseCode = "CSC207";
        GroupChat mockGroupChat = Mockito.mock(GroupChat.class);
        CreateCourseInputData inputData = new CreateCourseInputData(courseName, courseCode, mockGroupChat);

        when(mockDataAccessInterface.existsByCode(courseCode)).thenReturn(false);
        when(mockGroupChatFactory.create(courseCode)).thenReturn(mockGroupChat);

        // Create the course directly
        Course course = CourseFactory.create(courseName, courseCode, mockGroupChat);

        interactor.execute(inputData);

        ArgumentCaptor<CreateCourseOutputData> captor = ArgumentCaptor.forClass(CreateCourseOutputData.class);
        verify(mockOutputBoundary).prepareSuccessView(captor.capture());
        CreateCourseOutputData outputData = captor.getValue();

        assertEquals(courseName.toUpperCase(), outputData.getName()); // Expecting uppercase
        assertEquals(courseCode.toUpperCase(), outputData.getCode()); // Expecting uppercase
        assertEquals(mockGroupChat, outputData.getGroupChat());

        // Compare attributes of the Course object
        assertEquals(courseName.toUpperCase(), outputData.getCourse().getName()); // Expecting uppercase
        assertEquals(courseCode.toUpperCase(), outputData.getCourse().getCode()); // Expecting uppercase
        verify(mockDataAccessInterface).saveCourse(any(Course.class));
    }


}


