package data_access;

import entity.GroupChat;
import entity.User;
import entity.UserFactory;
import entity.UserFactoryInterface;
import data_access.UserSignupDataAccessInterface;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class UserDataAccessObject implements UserSignupDataAccessInterface{

    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, User> accounts = new HashMap<>();

    private UserFactoryInterface userFactory;

    public UserDataAccessObject(String csvPath, UserFactoryInterface userFactory) throws IOException {
        this.userFactory = userFactory;

        csvFile = new File(csvPath);
        headers.put("username", 0);
        headers.put("password", 1);
        headers.put("creation_time", 2);

        if (csvFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                assert header.equals("username,password,creation_time");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String name = String.valueOf(col[headers.get("name")]);
                    String username = String.valueOf(col[headers.get("username")]);
                    String password = String.valueOf(col[headers.get("password")]);
                    String email = String.valueOf(col[headers.get("email")]);
                    String coursesStr = col[headers.get("courses")];
                    List<String> courses = Arrays.asList(coursesStr.split(";"));
                    String groupchatStr = col[headers.get("groupchat")];
                    List<GroupChat> groupchat = Arrays.asList(groupchatStr.split(";")).stream()
                            .map(GroupChat::new) // Assuming you have a constructor in GroupChat that takes a string
                            .collect(Collectors.toList());
                    User user = userFactory.createUser(name, password, username, email, courses, groupchat);
                    accounts.put(username, user);
                }
            }
        }
    }

    @Override
    public void save(User user) {
        accounts.put(user.getName(), user);
        this.save();
    }

    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (User user : accounts.values()) {
                String line = "%s,%s,%s".formatted(
                        user.getName(), user.getPassword());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * Return whether a user exists with username identifier.
     * @param identifier the username to check.
     * @return whether a user exists with username identifier
     */
    @Override
    public boolean existsByName(String identifier) {
        return accounts.containsKey(identifier);
    }
}