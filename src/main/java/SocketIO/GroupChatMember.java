package SocketIO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

/**
 * The GroupChatMember class provides methods for managing group chat members and saving their details to a CSV file.
 */
public class GroupChatMember {
    private static final String CSV_FILE = "groupMember.csv";
    private Map<String, List<String>> courseMembers;

    public GroupChatMember() {
        courseMembers = new HashMap<>();
        loadMembersFromFile();
    }

    /**
     * Adds a member to the specified course group chat.
     * If the member already exists in the course group chat, they are not added again.
     *
     * @param courseCode the course code
     * @param member the member's chat handle
     */
    public void addMember(String courseCode, String member) {
        List<String> members = courseMembers.computeIfAbsent(courseCode, k -> new ArrayList<>());
        if (!members.contains(member)) {
            members.add(member);
            saveMembersToFile();
        }
    }

    public List<String> getMembersByCourseCode(String courseCode) {
        return courseMembers.getOrDefault(courseCode, new ArrayList<>());
    }

    /**
     * Loads the members from the CSV file into the courseMembers map.
     */
    private void loadMembersFromFile() {
        File file = new File(CSV_FILE);
        if (!file.exists()) {
            // File does not exist, so there's nothing to load
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String header = br.readLine();
            if (header == null) return;

            String[] courseCodes = header.split(",");

            String line;
            while ((line = br.readLine()) != null) {
                String[] members = line.split(",");
                for (int i = 0; i < members.length; i++) {
                    if (!members[i].trim().isEmpty()) {
                        courseMembers.computeIfAbsent(courseCodes[i], k -> new ArrayList<>()).add(members[i].trim());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves the members from the courseMembers map to the CSV file.
     */
    private void saveMembersToFile() {
        try (FileWriter fileWriter = new FileWriter(CSV_FILE);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            // Write the header
            List<String> courseCodes = new ArrayList<>(courseMembers.keySet());
            printWriter.println(String.join(",", courseCodes));

            // Find the maximum number of members in any course
            int maxMembers = courseMembers.values().stream().mapToInt(List::size).max().orElse(0);

            // Write the members
            for (int i = 0; i < maxMembers; i++) {
                StringBuilder row = new StringBuilder();
                for (String courseCode : courseCodes) {
                    List<String> members = courseMembers.get(courseCode);
                    if (i < members.size()) {
                        row.append(members.get(i));
                    }
                    row.append(",");
                }
                printWriter.println(row.toString().replaceAll(",$", ""));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        GroupChatMember groupChatMember = new GroupChatMember();
        groupChatMember.addMember("CSC207", "Lily");
        groupChatMember.addMember("CSC207", "Lucas");
        groupChatMember.addMember("PHY131", "Sarah");
        //groupChatMember.addMember("MAT141", "David");
        groupChatMember.addMember("CSC207", "Sarah");
        System.out.println("Members of CSC207: " + groupChatMember.getMembersByCourseCode("CSC207"));

    }
}
