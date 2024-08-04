package SocketIO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GroupChatPort {
    private static final String CSV_FILE = "groupchatPort.csv";

    public static void saveGroupChatDetails(String groupChatCode, int port, String host) {
        try (FileWriter fileWriter = new FileWriter(CSV_FILE, true);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.println(groupChatCode + "," + port + "," + host);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
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

    public static void main(String[] args) {
        String host = "100.66.6.84";
        int port = 4000;
        GroupChatPort.saveGroupChatDetails("CSC207", port, host);

    }

}
