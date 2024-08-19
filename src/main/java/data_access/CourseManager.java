package data_access;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages course-related operations, including appending course data to a CSV file
 * and checking the existence of courses based on their name or code.
 */
public class CourseManager {
    private String courseName;
    private String courseCode;
    private String groupChatName;

    /**
     * Constructs a CourseManager with the specified course details.
     *
     * @param courseName   the name of the course
     * @param courseCode   the code of the course
     * @param groupChatName the name of the group chat associated with the course
     */
    public CourseManager(String courseName, String courseCode, String groupChatName) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.groupChatName = groupChatName;
    }

    /**
     * Gets the name of the course.
     *
     * @return the course name
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Gets the code of the course.
     *
     * @return the course code
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * Gets the name of the group chat associated with the course.
     *
     * @return the group chat name
     */
    public String getGroupChatName() {
        return groupChatName;
    }

    /**
     * Appends the courses information to a CSV file named courses.csv.
     *
     * @param courses the list of courses to write to the file
     */
    public static void appendCoursesToCSV(List<CourseManager> courses) {
        boolean fileExists = new java.io.File("courses.csv").exists();

        try (PrintWriter writer = new PrintWriter(new FileWriter("courses.csv", true))) {
            // Write the header only if the file does not already exist
            if (!fileExists) {
                writer.println("Course Name,Course Code,Group Chat Name");
            }

            // Write the course data
            for (CourseManager course : courses) {
                writer.printf("%s,%s,%s%n", course.getCourseName(), course.getCourseCode(), course.getGroupChatName());
            }

            System.out.println("Courses information has been appended to courses.csv");
        } catch (IOException e) {
            System.err.println("Error writing to courses.csv: " + e.getMessage());
        }
    }


    /**
     * Checks if a course exists in the CSV file based on the course name.
     *
     * @param courseName the name of the course to check
     * @return true if the course exists, false otherwise
     */
    public static boolean courseExistsByName(String courseName) {
        try (BufferedReader reader = new BufferedReader(new FileReader("courses.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 0 && parts[0].equalsIgnoreCase(courseName)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from courses.csv: " + e.getMessage());
        }
        return false;
    }

    /**
     * Checks if a course exists in the CSV file based on the course code.
     *
     * @param courseCode the code of the course to check
     * @return true if the course exists, false otherwise
     */
    public static boolean courseExistsByCode(String courseCode) {
        try (BufferedReader reader = new BufferedReader(new FileReader("courses.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 1 && parts[1].equalsIgnoreCase(courseCode)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from courses.csv: " + e.getMessage());
        }
        return false;
    }

    /**
     * Gets the course code given a course name.
     *
     * @param courseName the name of the course
     * @return the course code if found, null otherwise
     */
    public static String getCourseCodeByName(String courseName) {
        try (BufferedReader reader = new BufferedReader(new FileReader("courses.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 1 && parts[0].equalsIgnoreCase(courseName)) {
                    return parts[1];
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading from courses.csv: " + e.getMessage());
        }
        return null;
    }

}
