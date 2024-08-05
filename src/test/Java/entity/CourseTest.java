package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CourseTest {

    private Course course;
    private GroupChat groupChat;

    @BeforeEach
    public void setUp() {
        groupChat = new GroupChat("CSC207");
        course = new Course("Software Design", "CSC207", groupChat);
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals("SOFTWARE DESIGN", course.getName());
        assertEquals("CSC207", course.getCode());
    }

    @Test
    public void testSetName() {
        course.setName("Computer Science");
        assertEquals("Computer Science", course.getName());
    }

    @Test
    public void testSetCode() {
        course.setCode("CSC208");
        assertEquals("CSC208", course.getCode());
    }

    @Test
    public void testSetGroupChat() {
        GroupChat newGroupChat = new GroupChat("CSC208");
        course.setGroupchat(newGroupChat);
        assertEquals(newGroupChat, course.getGroupchat());
    }

    @Test
    public void testToString() {
        String expected = "Course name: SOFTWARE DESIGN, Course code: CSC207";
        assertEquals(expected, course.toString());
    }
}
