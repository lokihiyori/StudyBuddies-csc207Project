package data_access;

import entity.Course;
import entity.GroupChat;
import entity.CourseFactory;
import entity.GroupChatFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CourseDataAccessObjectTest {
    private CourseDataAccessObject dao;
    private CourseFactory courseFactory;
    private GroupChatFactory groupChatFactory;
    private final String csvFilePath = "test_courses.csv";

    @BeforeEach
    public void setUp() throws IOException {
        courseFactory = new CourseFactory();

        groupChatFactory = new GroupChatFactory();

        // Initialize DAO with a test CSV file
        dao = new CourseDataAccessObject(csvFilePath, courseFactory, groupChatFactory);
    }

    @Test
    public void testSaveCourse() {
        Course course = CourseFactory.create("Test Course", "TC101", groupChatFactory.create("GC101"));
        dao.saveCourse(course);

        Map<String, Course> courseMap = dao.getCourseMap();
        assertTrue(courseMap.containsKey("TC101"));
        assertEquals("TEST COURSE", courseMap.get("TC101").getName());
    }

    @Test
    public void testExistsByCode() {
        Course course = CourseFactory.create("Test Course", "TC101", groupChatFactory.create("GC101"));
        dao.saveCourse(course);

        assertTrue(dao.existsByCode("TC101"));
        assertFalse(dao.existsByCode("TC102"));
    }

    @Test
    public void testGetByCode() {
        Course course = CourseFactory.create("Test Course", "TC101", groupChatFactory.create("GC101"));
        dao.saveCourse(course);

        Course retrievedCourse = dao.getByCode("TC101");
        assertNotNull(retrievedCourse);
        assertEquals("TEST COURSE", retrievedCourse.getName());
    }


    // Clean up the test CSV file after tests
    @AfterEach
    public void tearDown() {
        File file = new File(csvFilePath);
        if (file.exists()) {
            file.delete();
        }
    }
}
