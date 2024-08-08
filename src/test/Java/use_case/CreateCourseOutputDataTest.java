package use_case;

import entity.Course;
import entity.GroupChat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.CreateCourse.CreateCourseOutputData;

import static org.junit.jupiter.api.Assertions.*;

class CreateCourseOutputDataTest {

    private CreateCourseOutputData outputData;
    private Course course;
    private GroupChat groupChat;

    @BeforeEach
    void setUp() {
        String courseName = "Software Engineering";
        String courseCode = "CSC207";
        groupChat = new GroupChat(courseCode);
        course = new Course(courseName, courseCode, groupChat);
        outputData = new CreateCourseOutputData(courseName, courseCode, groupChat, course);
    }

    @Test
    void testGetName() {
        assertEquals("Software Engineering", outputData.getName());
    }

    @Test
    void testSetName() {
        outputData.setName("Data Structures");
        assertEquals("Data Structures", outputData.getName());
    }

    @Test
    void testGetCode() {
        assertEquals("CSC207", outputData.getCode());
    }

    @Test
    void testSetCode() {
        outputData.setCode("CSC209");
        assertEquals("CSC209", outputData.getCode());
    }

    @Test
    void testGetGroupChat() {
        assertEquals(groupChat, outputData.getGroupChat());
    }

    @Test
    void testSetGroupChat() {
        GroupChat newGroupChat = new GroupChat("CSC209");
        outputData.setGroupChat(newGroupChat);
        assertEquals(newGroupChat, outputData.getGroupChat());
    }

    @Test
    void testGetCourse() {
        assertEquals(course, outputData.getCourse());
    }

    @Test
    void testSetCourse() {
        Course newCourse = new Course("Data Structures", "CSC209", new GroupChat("CSC209"));
        outputData.setCourse(newCourse);
        assertEquals(newCourse, outputData.getCourse());
    }
}

