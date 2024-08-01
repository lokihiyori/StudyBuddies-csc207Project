package data_access;

import entity.Course;
import entity.GroupChat;
import entity.GroupChatFactory;
import use_case.CreateGroupChat.CreateGroupChatDataAccessInterface;

import java.io.*;
import java.util.ArrayList;
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
        if (csvFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();
                assert header.equals("GroupChat Code,GroupChat Members,GroupChat Message History");
                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String groupChatCode = String.valueOf(col[headers.get("GroupChat Code")]);
                    String members = String.valueOf(col[headers.get("GroupChat Members")]);
                    String messageHistory = col[headers.get("GroupChat Message History")];

                    GroupChat groupChat = groupChatFactory.create(groupChatCode);
                    groupChats.put(groupChatCode, groupChat);
                }
            }
        }

    }

    private void save() throws IOException{
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (GroupChat groupChat : groupChats.values()) {
                String groupChatCode = groupChat.getCode();
                String members = String.join(";", groupChat.getGroupMembers().toString());
                String messageHistory = String.join(";", groupChat.getMessageHistory().toString());
                String line = "%s,%s,%s".formatted(groupChatCode, members, messageHistory);
                writer.write(line);
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveGroupChat(GroupChat groupChat) throws IOException {
        groupChats.put(groupChat.getCode(), groupChat);
        this.save();
    }


    @Override
    public GroupChat getGroupChat(String code) {
        return groupChats.get(code);
    }

    @Override
    public boolean existsByCode(String code) {
        return groupChats.get(code) != null;}
}

