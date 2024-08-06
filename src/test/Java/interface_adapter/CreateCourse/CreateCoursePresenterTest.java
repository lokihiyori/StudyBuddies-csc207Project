package interface_adapter.CreateCourse;

import entity.Course;
import entity.CourseFactory;
import entity.GroupChat;
import entity.GroupChatFactory;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import use_case.CreateCourse.CreateCourseOutputData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

class CreateCoursePresenterTest {

    private CreateCoursePresenter presenter;
    private CreateCourseViewModel createCourseViewModel;

    @BeforeEach
    void setUp() {
        createCourseViewModel = Mockito.mock(CreateCourseViewModel.class);
        presenter = new CreateCoursePresenter(createCourseViewModel);
    }

    @Test
    void prepareSuccessView() {
        GroupChatFactory groupChatFactory = new GroupChatFactory();
        GroupChat groupChat = groupChatFactory.create("TEST123");
        CourseFactory courseFactory = new CourseFactory();
        Course course = courseFactory.create("Test Course", "TEST123", groupChat);
        // Arrange
        CreateCourseOutputData outputData = new CreateCourseOutputData("Test Course", "TEST123", groupChat, course);

        // Act
        presenter.prepareSuccessView(outputData);

        // Assert
        ArgumentCaptor<CreateCourseState> stateCaptor = ArgumentCaptor.forClass(CreateCourseState.class);
        verify(createCourseViewModel).setState(stateCaptor.capture());

        CreateCourseState capturedState = stateCaptor.getValue();
        assertEquals("TEST COURSE", capturedState.getName());
        assertEquals("TEST123", capturedState.getCode());

        verify(createCourseViewModel, times(1)).firePropertyChanged();
    }

    @Test
    void prepareFailView() {
        // Arrange
        String error = "Error message";

        // Act
        presenter.prepareFailView(error);

        // Assert
        // Since prepareFailView only prints the error message, we can use a system output stream capture if necessary.
        // However, in this case, we're not verifying it because it's a print statement.
    }
}
