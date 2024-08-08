package interface_adapter;

import entity.Course;
import entity.GroupChat;
import interface_adapter.SearchCourse.SearchCoursePresenter;
import interface_adapter.SearchCourse.SearchCourseViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.SearchCourse.SearchCourseOutputData;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SearchCoursePresenterTest {

    private SearchCoursePresenter presenter;
    private SearchCourseViewModel viewModel;
    private Course sampleCourse;
    private GroupChat sampleGroupChat;

    @BeforeEach
    void setUp() {
        viewModel = new SearchCourseViewModel();
        presenter = new SearchCoursePresenter(viewModel);
        sampleGroupChat = new GroupChat("CS101");
        sampleCourse = new Course("Introduction to Computer Science", "CS101", sampleGroupChat);
    }

    @Test
    void testPresent() {
        // Arrange
        String sampleMessage = "Course found successfully";
        SearchCourseOutputData outputData = new SearchCourseOutputData(sampleCourse, sampleMessage);

        // Act
        presenter.present(outputData);

        // Assert
        assertEquals(sampleCourse, viewModel.getCourse(), "The course should match the output data");
        assertEquals(sampleMessage, viewModel.getMessage(), "The message should match the output data");
    }
}
