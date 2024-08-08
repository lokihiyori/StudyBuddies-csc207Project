package interface_adapter;

import entity.Course;
import entity.GroupChat;
import interface_adapter.SearchCourse.SearchCourseViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class SearchCourseViewModelTest {

    private SearchCourseViewModel viewModel;
    private Course sampleCourse;
    private GroupChat sampleGroupChat;

    @BeforeEach
    void setUp() {
        viewModel = new SearchCourseViewModel();
        sampleGroupChat = new GroupChat("CS101");
        sampleCourse = new Course("Introduction to Computer Science", "CS101", sampleGroupChat);
    }

    @Test
    void testSetAndGetCourse() {
        // Initially, the course should be null
        assertNull(viewModel.getCourse(), "Course should initially be null");

        // Set a course and verify it's correctly set
        viewModel.setCourse(sampleCourse);
        assertEquals(sampleCourse, viewModel.getCourse(), "The course should match what was set");
    }

    @Test
    void testSetAndGetMessage() {
        // Initially, the message should be null
        assertNull(viewModel.getMessage(), "Message should initially be null");

        // Set a message and verify it's correctly set
        String sampleMessage = "Course found successfully";
        viewModel.setMessage(sampleMessage);
        assertEquals(sampleMessage, viewModel.getMessage(), "The message should match what was set");
    }
}

