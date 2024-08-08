package use_case;

import entity.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import use_case.SearchCourse.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SearchCourseInteractorTest {

    private CourseRepository courseRepository;
    private SearchCourseOutputBoundary outputBoundary;
    private SearchCourseInteractor interactor;

    @BeforeEach
    void setUp() {
        courseRepository = mock(CourseRepository.class);
        outputBoundary = mock(SearchCourseOutputBoundary.class);
        interactor = new SearchCourseInteractor(courseRepository, outputBoundary);
    }

    @Test
    void testSearchCourse_FoundByCode() {
        // Arrange
        SearchCourseInputData inputData = new SearchCourseInputData("CSC207");
        Course course = new Course("Software Engineering", "CSC207", null);

        when(courseRepository.findCourseByCode("CSC207")).thenReturn(course);
        when(courseRepository.findCourseByName("CSC207")).thenReturn(null);

        // Act
        interactor.searchCourse(inputData);

        // Assert
        ArgumentCaptor<SearchCourseOutputData> captor = ArgumentCaptor.forClass(SearchCourseOutputData.class);
        verify(outputBoundary).present(captor.capture());
        SearchCourseOutputData outputData = captor.getValue();

        assertEquals(course, outputData.getCourse());
        assertEquals("Course found by code: " + course, outputData.getMessage());
    }

    @Test
    void testSearchCourse_FoundByName() {
        // Arrange
        SearchCourseInputData inputData = new SearchCourseInputData("Software Engineering");
        Course course = new Course("Software Engineering", "CSC207", null);

        when(courseRepository.findCourseByCode("Software Engineering")).thenReturn(null);
        when(courseRepository.findCourseByName("Software Engineering")).thenReturn(course);

        // Act
        interactor.searchCourse(inputData);

        // Assert
        ArgumentCaptor<SearchCourseOutputData> captor = ArgumentCaptor.forClass(SearchCourseOutputData.class);
        verify(outputBoundary).present(captor.capture());
        SearchCourseOutputData outputData = captor.getValue();

        assertEquals(course, outputData.getCourse());
        assertEquals("Course found by Name: " + course, outputData.getMessage());
    }

    @Test
    void testSearchCourse_NotFound() {
        // Arrange
        SearchCourseInputData inputData = new SearchCourseInputData("NonExistentCourse");

        when(courseRepository.findCourseByCode("NonExistentCourse")).thenReturn(null);
        when(courseRepository.findCourseByName("NonExistentCourse")).thenReturn(null);

        // Act
        interactor.searchCourse(inputData);

        // Assert
        ArgumentCaptor<SearchCourseOutputData> captor = ArgumentCaptor.forClass(SearchCourseOutputData.class);
        verify(outputBoundary).present(captor.capture());
        SearchCourseOutputData outputData = captor.getValue();

        assertEquals(null, outputData.getCourse());
        assertEquals("Course not found.", outputData.getMessage());
    }
}
