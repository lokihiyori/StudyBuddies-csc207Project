package SocketIO;

import data_access.CourseDataAccessObject;
import data_access.CourseManager;
import entity.CourseFactory;
import entity.GroupChatFactory;
import interface_adapter.CreateCourse.CreateCourseController;
import interface_adapter.CreateCourse.CreateCoursePresenter;
import interface_adapter.CreateCourse.CreateCourseViewModel;
import use_case.CreateCourse.CreateCourseInteractor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * The GroupChatPort class provides methods for saving and retrieving group chat details from a CSV file.
 */
public class GroupChatPort {
    private static final String CSV_FILE = "groupchatPort.csv";

    /**
     * Saves the group chat details to the CSV file.
     *
     * @param groupChatCode the group chat code
     * @param port the port number
     * @param host the host address
     */
    public static void saveGroupChatDetails(String groupChatCode, int port, String host) {
        try (FileWriter fileWriter = new FileWriter(CSV_FILE, true);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.println(groupChatCode + "," + port + "," + host);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the largest port number from the CSV file.
     *
     * @return the largest port number, or -1 if no port number is found
     */
    public static int getLargestPortNumber() {
        int largestPort = -1;

        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 2) {
                    int port = Integer.parseInt(fields[1].trim());
                    if (port > largestPort) {
                        largestPort = port;
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return largestPort;
    }

    /**
     * Retrieves the port number associated with the specified course code from the CSV file.
     *
     * @param courseCode the course code
     * @return the port number, or -1 if the course code is not found
     */
    public static int getPortByCourseCode(String courseCode) {
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 2) {
                    String code = fields[0].trim();
                    int port = Integer.parseInt(fields[1].trim());
                    if (code.equals(courseCode)) {
                        return port;
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return -1; // Return -1 if the course code is not found
    }

    /**
     * Retrieves the host address from the first line of the CSV file.
     *
     * @return the host address, or null if the host address is not found
     */
    public static String getHostFromFirstLine() {
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            String line = br.readLine();
            if (line != null) {
                String[] fields = line.split(",");
                if (fields.length >= 3) {
                    return fields[2].trim();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null; // Return null if the host address is not found
    }

    /**
     * The main method to initialize and save group chat details and append courses to a CSV file.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // actual IP address of the server machine (Yujing's laptop)
        String host = "10.0.0.46";
        int port = 4000;
        GroupChatPort.saveGroupChatDetails("CSC207", port, host);


        List<CourseManager> courses = new ArrayList<>();
        courses.add(new CourseManager("SOFTWARE DESIGN", "CSC207", "CSC207"));

        // Write the courses to CSV
        CourseManager.appendCoursesToCSV(courses);



    }

}
