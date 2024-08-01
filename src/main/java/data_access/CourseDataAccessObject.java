package data_access;

import entity.*;
import use_case.CreateCourse.CreateCourseDataAccessInterface;
import use_case.SearchCourse.CourseRepository;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

import entity.Course;
import use_case.SearchCourse.CourseRepository;

public class CourseDataAccessObject implements CreateCourseDataAccessInterface, CourseRepository {
    private final File csvFile;
    private final Map<String, Course> courseMap = new HashMap<>();
    private final CourseFactory courseFactory;
    private final GroupChatFactory groupChatFactory;
    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private List<Course> courses;

    public CourseDataAccessObject() {
        this.csvFile = null;
        this.courseFactory = null;
        this.groupChatFactory = null;
        this.courses = new ArrayList<>();
    }

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
    public void saveCourse(Course course) {
        courseMap.put(course.getCode(), course);
        this.save();
    }

    public Map<String, Course> getCourseMap() {
        return courseMap;
    }

    @Override
    public boolean existsByCode(String code) {
        return courseMap.containsKey(code);
    }
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

    public void addCourse(Course course) {
        courses.add(course);
    }

    @Override
    public Course findCourseByCode(String courseCode) {
        for (Course course : courses) {
            if (course.getCode().equals(courseCode.toUpperCase())) {
                return course;
            }
        }
        return null;
    }

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
