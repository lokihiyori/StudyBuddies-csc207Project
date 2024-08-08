package interface_adapter;

import interface_adapter.SearchCourse.SearchCourseController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import use_case.SearchCourse.SearchCourseInputBoundary;
import use_case.SearchCourse.SearchCourseInputData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class SearchCourseControllerTest {

    private SearchCourseController searchCourseController;
    private SearchCourseInputBoundary mockInputBoundary;

    @BeforeEach
    void setUp() {
        mockInputBoundary = Mockito.mock(SearchCourseInputBoundary.class);
        searchCourseController = new SearchCourseController(mockInputBoundary);
    }

    @Test
    void testHandleInput() {
        // Arrange
        String query = "test query";

        // Act
        searchCourseController.handleInput(query);

        // Assert
        ArgumentCaptor<SearchCourseInputData> captor = ArgumentCaptor.forClass(SearchCourseInputData.class);
        verify(mockInputBoundary, times(1)).searchCourse(captor.capture());
        SearchCourseInputData capturedInputData = captor.getValue();
        assertEquals(query, capturedInputData.getQuery());
    }
}

