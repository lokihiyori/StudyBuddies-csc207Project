package data_access;

import entity.*;
import use_case.CreateCourse.CreateCourseDataAccessInterface;
import use_case.SearchCourse.CourseRepository;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

import entity.Course;
import use_case.SearchCourse.CourseRepository;

/**
 * Implementation of data access for courses, providing functionality to
 * read, write, and manage course data in a CSV file.
 */
public class CourseDataAccessObject implements CreateCourseDataAccessInterface, CourseRepository {
    private final File csvFile;
    private final Map<String, Course> courseMap = new HashMap<>();
    private final CourseFactory courseFactory;
    private final GroupChatFactory groupChatFactory;
    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private List<Course> courses;

    /**
     * Constructs a CourseDataAccessObject with default settings.
     */
    public CourseDataAccessObject() {
        this.csvFile = null;
        this.courseFactory = null;
        this.groupChatFactory = null;
        this.courses = new ArrayList<>();
    }

    /**
     * Constructs a CourseDataAccessObject with specified CSV path and factories.
     *
     * @param csvPath            the path to the CSV file for storing course data
     * @param courseFactory      the factory for creating Course objects
     * @param groupChatFactory   the factory for creating GroupChat objects
     * @throws IOException if an I/O error occurs while reading or writing the CSV file
     */
    public CourseDataAccessObject(String csvPath, CourseFactory courseFactory, GroupChatFactory groupChatFactory) throws IOException {
        this.courseFactory = courseFactory;
        this.groupChatFactory = groupChatFactory;
        this.csvFile = new File(csvPath);
        headers.put("Course name", 0);
        headers.put("Course code", 1);
        headers.put("Group chat", 2);

        if (csvFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();
                assert header.equals("Course name,Course code,Group chat");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String courseName = String.valueOf(col[headers.get("Course name")]);
                    String courseCode = String.valueOf(col[headers.get("Course code")]);
                    String groupChatCode = col[headers.get("Group chat")];
                    GroupChat groupChat = groupChatFactory.create(groupChatCode);
                    Course course = courseFactory.create(courseName, courseCode, groupChat);
                    courseMap.put(courseCode, course);
                }
            }
        }
    }

    /**
     * Saves the specified course to the course map and writes the updated data to the CSV file.
     *
     * @param course the Course object to save
     */
    public void saveCourse(Course course) {
        courseMap.put(course.getCode(), course);
        this.save();
    }

    /**
     * Gets the map of courses indexed by their codes.
     *
     * @return a map where the key is the course code and the value is the Course object
     */
    public Map<String, Course> getCourseMap() {
        return courseMap;
    }

    /**
     * Checks if a course with the specified code exists in the data store.
     *
     * @param code the course code to check
     * @return true if a course with the given code exists, false otherwise
     */
    @Override
    public boolean existsByCode(String code) {
        return courseMap.containsKey(code);
    }

    /**
     * Retrieves a course by its code.
     *
     * @param code the course code
     * @return the Course object associated with the given code, or null if not found
     */
    public Course getByCode(String code){return courseMap.get(code); }

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (Course course : courseMap.values()) {
                String line = "%s,%s,%s".formatted(
                        course.getName(), course.getCode(), course.getGroupchat().getCode());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Adds a course to the internal list of courses.
     *
     * @param course the Course object to add
     */
    public void addCourse(Course course) {
        courses.add(course);
    }

    /**
     * Finds a course by its code from the internal list of courses.
     *
     * @param courseCode the course code to search for
     * @return the Course object associated with the given code, or null if not found
     */
    @Override
    public Course findCourseByCode(String courseCode) {
        for (Course course : courses) {
            if (course.getCode().equals(courseCode.toUpperCase())) {
                return course;
            }
        }
        return null;
    }

    /**
     * Finds a course by its name from the internal list of courses.
     *
     * @param courseName the course name to search for
     * @return the Course object associated with the given name, or null if not found
     */
    @Override
    public Course findCourseByName(String courseName) {
        for (Course course : courses) {
            if (course.getName().equalsIgnoreCase(courseName.toUpperCase())) {
                return course;
            }
        }
        return null;
    }

}
