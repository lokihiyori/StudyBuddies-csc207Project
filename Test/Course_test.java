
import entity.Course;
import entity.GroupChat;
import org.junit.Test;
import static org.junit.Assert.*;

public class Course_test {

    // creates a Course object and checks if the constructor correctly initializes the attributes
    @Test
    public void testCourseConstructor() {
        GroupChat chat = new GroupChat("CSC207");
        Course course = new Course("Software Design", "CSC207", chat);

        assertEquals("Software Design", course.getName());
        assertEquals("CSC207", course.getCode());
        assertEquals("CSC207_Cluster", course.getGroupchat());
    }

    // test the setter methods by changing the attribute values and asserting the changes
    @Test
    public void testSetName() {
        GroupChat chat = new GroupChat("CSC207");
        Course course = new Course("Software Design", "CSC207", chat);
        course.setName("Software Tools and Systems Programming");

        assertEquals("Software Tools and Systems Programming", course.getName());
    }


    @Test
    public void testSetCode() {
        GroupChat chat = new GroupChat("CSC207");
        Course course = new Course("Software Design", "CSC207", chat);
        course.setCode("CSC209");

        assertEquals("CSC209", course.getCode());
    }


    @Test
    public void testSetGroupchat() {
        GroupChat chat = new GroupChat("CSC207");
        Course course = new Course("Software Design", "CSC207", chat);
        GroupChat newGroupChat = new GroupChat("CSC209");
        course.setGroupchat(newGroupChat);

        assertEquals(newGroupChat, course.getGroupchat());
    }

    // checks if the toString method returns the expected string representation of the Course object
    @Test
    public void testToString() {
        GroupChat chat = new GroupChat("CSC207");
        Course course = new Course("Software Design", "CSC207", chat);

        String expected_output = "Course name: Software Design, Course code: CSC207";
        assertEquals(expected_output, course.toString());
    }


}