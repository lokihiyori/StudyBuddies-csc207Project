package data_access;

import entity.*;
import use_case.CreateCourse.CreateCourseDataAccessInterface;

import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class CourseDataAccessObject implements CreateCourseDataAccessInterface {
    private final File csvFile;
    private final Map<String, Course> courseMap = new HashMap<>();
    private final CourseFactory courseFactory;
    private final GroupChatFactory groupChatFactory;
    private final Map<String, Integer> headers = new LinkedHashMap<>();

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
    }
