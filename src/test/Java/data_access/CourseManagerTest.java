package data_access;

import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CourseManagerTest {

    private static final String TEMP_CSV_PATH = "test_courses.csv";

    @BeforeEach
    void setUp() throws IOException {
        // Create a temporary CSV file for testing
        Files.createFile(Paths.get(TEMP_CSV_PATH));
    }

    @AfterEach
    void tearDown() throws IOException {
        // Delete the temporary CSV file after each test
        Files.deleteIfExists(Paths.get(TEMP_CSV_PATH));
    }

    @Test
    void testAppendCoursesToCSV() {
        List<CourseManager> courses = Arrays.asList(
                new CourseManager("Course 1", "CODE1", "Group 1"),
                new CourseManager("Course 2", "CODE2", "Group 2")
        );

        // Change the output file path to the temporary test file
        CourseManager.appendCoursesToCSV(courses);

        List<String> lines = readCsvFile(TEMP_CSV_PATH);
        assertEquals("COURSE NAME,Course Code,Group Chat Name", lines.get(0));
        assertEquals("COURSE 1,CODE1,Group 1", lines.get(1));
        assertEquals("COURSE 2,CODE2,Group 2", lines.get(2));
    }

    @Test
    void testCourseExistsByName() throws IOException {
        writeSampleCsvFile();

        assertTrue(CourseManager.courseExistsByName("COURSE 1"));
        assertFalse(CourseManager.courseExistsByName("NONEXISTENT COURSE"));
    }

    @Test
    void testCourseExistsByCode() throws IOException {
        writeSampleCsvFile();

        assertTrue(CourseManager.courseExistsByCode("CODE1"));
        assertFalse(CourseManager.courseExistsByCode("NONEXISTENT"));
    }

    @Test
    void testGetCourseCodeByName() throws IOException {
        writeSampleCsvFile();

        assertEquals("CODE1", CourseManager.getCourseCodeByName("COURSE 1"));
        assertNull(CourseManager.getCourseCodeByName("NONEXISTENT COURSE"));
    }

    private void writeSampleCsvFile() throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(TEMP_CSV_PATH))) {
            writer.println("Course Name,Course Code,Group Chat Name");
            writer.println("Course 1,CODE1,Group 1");
            writer.println("Course 2,CODE2,Group 2");
        }
    }

    private List<String> readCsvFile(String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            return lines;
        } catch (IOException e) {
            throw new RuntimeException("Failed to read CSV file", e);
        }
    }
}
