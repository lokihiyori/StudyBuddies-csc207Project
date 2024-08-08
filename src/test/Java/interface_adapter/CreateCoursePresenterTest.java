package interface_adapter;

import interface_adapter.CreateCourse.CreateCoursePresenter;
import interface_adapter.CreateCourse.CreateCourseState;
import interface_adapter.CreateCourse.CreateCourseViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import use_case.CreateCourse.CreateCourseOutputData;
import entity.Course;
import entity.GroupChat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

class CreateCoursePresenterTest {
    @Mock
    private CreateCourseViewModel mockViewModel;

    @InjectMocks
    private CreateCoursePresenter presenter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testPrepareSuccessView() {
        // Arrange
        GroupChat mockGroupChat = mock(GroupChat.class);
        Course mockCourse = mock(Course.class);
        CreateCourseOutputData outputData = new CreateCourseOutputData("Software Engineering", "SE123", mockGroupChat, mockCourse);

        // Use argThat to define conditions for argument matching
        // This matcher checks if the state contains the correct name and code
        ArgumentMatcher<CreateCourseState> matcher = state ->
                "Software Engineering".equals(state.getName()) && "SE123".equals(state.getCode());

        // Act
        presenter.prepareSuccessView(outputData);

        // Assert
        verify(mockViewModel).setState(argThat(matcher));
        verify(mockViewModel).firePropertyChanged();
    }

    @Test
    void testPrepareFailView() {
        String error = "Invalid course code";

        // Capture the System.out.println output
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        // Act
        presenter.prepareFailView(error);

        // Assert
        String expectedOutput = "Error: Invalid course code"; // Removed the newline character
        String actualOutput = outContent.toString().trim(); // Trim to remove any new line or trailing spaces

        assertEquals(expectedOutput, actualOutput);

        // Restore original System.out
        System.setOut(originalOut);
}}

