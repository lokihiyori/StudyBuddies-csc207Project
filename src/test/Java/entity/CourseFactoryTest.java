package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CourseFactoryTest {

    private GroupChat groupChat;

    @BeforeEach
    public void setUp() {
        groupChat = new GroupChat("CSC207");
    }

    @Test
    public void testCreate() {
        String name = "Software Design";
        String code = "CSC207";

        Course course = CourseFactory.create(name, code, groupChat);

        assertNotNull(course);
        assertEquals("SOFTWARE DESIGN", course.getName());
        assertEquals("CSC207", course.getCode());
    }
}
