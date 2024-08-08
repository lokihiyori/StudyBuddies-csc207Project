package data_access;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CourseManagerTest {
    private final String testCsvPath = "courses.csv";  // This should match the path used in CourseManager

    @BeforeEach
    public void setUp() throws IOException {
        // Ensure the test CSV file is empty before each test
        new FileWriter(testCsvPath, false).close();
    }

    @Test
    public void testAppendCoursesToCSV() {
        List<CourseManager> courses = new ArrayList<>();
        courses.add(new CourseManager("Test Course 1", "TC101", "GC101"));
        courses.add(new CourseManager("Test Course 2", "TC102", "GC102"));

        // Call the method to append courses
        CourseManager.appendCoursesToCSV(courses);

        try (BufferedReader reader = new BufferedReader(new FileReader(testCsvPath))) {
            String header = reader.readLine();
            assertEquals("Test Course 1,TC101,GC101", header);

            String line1 = reader.readLine();
            assertEquals("Test Course 2,TC102,GC102", line1);


        } catch (IOException e) {
            fail("Error reading test CSV file: " + e.getMessage());
        }
    }

    @Test
    public void testCourseExistsByName() {
        List<CourseManager> courses = new ArrayList<>();
        courses.add(new CourseManager("Test Course 1", "TC101", "GC101"));
        CourseManager.appendCoursesToCSV(courses);

        // Check if the course exists by name
        assertTrue(CourseManager.courseExistsByName("Test Course 1"));
        assertFalse(CourseManager.courseExistsByName("Nonexistent Course"));
    }

    @Test
    public void testCourseExistsByCode() {
        List<CourseManager> courses = new ArrayList<>();
        courses.add(new CourseManager("Test Course 1", "TC101", "GC101"));
        CourseManager.appendCoursesToCSV(courses);

        // Check if the course exists by code
        assertTrue(CourseManager.courseExistsByCode("TC101"));
        assertFalse(CourseManager.courseExistsByCode("TC999"));
    }

    @Test
    public void testGetCourseCodeByName() {
        List<CourseManager> courses = new ArrayList<>();
        courses.add(new CourseManager("Test Course 1", "TC101", "GC101"));
        CourseManager.appendCoursesToCSV(courses);

        // Get the course code by name
        assertEquals("TC101", CourseManager.getCourseCodeByName("Test Course 1"));
        assertNull(CourseManager.getCourseCodeByName("Nonexistent Course"));
    }

    @AfterEach
    public void tearDown() {
        // Clean up the test CSV file after tests
        File file = new File(testCsvPath);
        if (file.exists()) {
            file.delete();
        }
    }
}
