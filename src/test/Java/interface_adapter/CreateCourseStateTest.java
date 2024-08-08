package interface_adapter;

import interface_adapter.CreateCourse.CreateCourseState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreateCourseStateTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        String name = "Test Course";
        String code = "TEST123";

        // Act
        CreateCourseState state = new CreateCourseState(name, code);

        // Assert
        assertEquals(name, state.getName());
        assertEquals(code, state.getCode());
    }

    @Test
    void testSetters() {
        // Arrange
        CreateCourseState state = new CreateCourseState();
        String name = "Test Course";
        String code = "TEST123";

        // Act
        state.setName(name);
        state.setCode(code);

        // Assert
        assertEquals(name, state.getName());
        assertEquals(code, state.getCode());
    }
}
