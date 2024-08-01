package data_access;

import entity.GroupChat;
import entity.GroupChatFactory;
import use_case.CreateGroupChat.CreateGroupChatDataAccessInterface;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class GroupChatDataAccessObject implements CreateGroupChatDataAccessInterface {
    private final File csvFile;
    private final Map<String, GroupChat> groupChats = new HashMap<>();
    private final Map<String, Integer> headers = new LinkedHashMap<>();

    public GroupChatDataAccessObject(String csvPath, GroupChatFactory groupChatFactory) throws IOException {
        this.csvFile = new File(csvPath);
        headers.put("GroupChat Code", 0);
        headers.put("GroupChat Members", 1);
        headers.put("GroupChat Message History", 2);

        if (!csvFile.exists() || csvFile.length() == 0) {
            save();
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();
                if (!header.equals("GroupChat Code,GroupChat Members,GroupChat Message History")) {
                    throw new IOException("CSV file header is incorrect.");
                }
                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String groupChatCode = col[headers.get("GroupChat Code")];
                    String members = col[headers.get("GroupChat Members")];
                    String messageHistory = col[headers.get("GroupChat Message History")];

                    GroupChat groupChat = groupChatFactory.create(groupChatCode);
                    groupChats.put(groupChatCode, groupChat);
                }
            }
        }
    }

    private void save() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(csvFile))) {
            writer.println("GroupChat Code,GroupChat Members,GroupChat Message History");

            for (GroupChat groupChat : groupChats.values()) {
                String groupChatCode = groupChat.getCode();
                String members = String.join(";", groupChat.getGroupMembers());
                String messageHistory = String.join(";", groupChat.getMessageHistory());

                writer.println(String.format("%s,%s,%s", groupChatCode, members, messageHistory));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveGroupChat(GroupChat groupChat) {
        groupChats.put(groupChat.getCode(), groupChat);
    }

    @Override
    public GroupChat getGroupChat(String code) {
        return groupChats.get(code);
    }

    @Override
    public boolean existsByCode(String code) {
        return groupChats.containsKey(code);
    }
}
