package data_access;

import entity.User;
import entity.UserFactory;
import use_case.Login.LoginUserDataAccessInterface;
import use_case.MakeEvent.makeEventUserDataAccessInterface;
import use_case.Signup.SignupUserDataAccessInterface;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Handles user data access and storage using a CSV file.
 * Implements functionalities for user signup, login, and event management.
 */
public class FileUserDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface, makeEventUserDataAccessInterface {

    private final File csvFile;

    private final Map<String, Integer> headers = new LinkedHashMap<>();

    private final Map<String, User> accounts = new HashMap<>();

    private UserFactory userFactory;

    /**
     * Constructs a FileUserDataAccessObject with the specified CSV file path and user factory.
     *
     * @param csvPath     the path to the CSV file for user data
     * @param userFactory the factory to create User objects
     * @throws IOException if an I/O error occurs while reading the file
     */
    public FileUserDataAccessObject(String csvPath, UserFactory userFactory) throws IOException {
        this.userFactory = userFactory;

        csvFile = new File(csvPath);
        headers.put("username", 0);
        headers.put("email", 1);
        headers.put("password", 2);
        headers.put("creation_time", 3);

        if (csvFile.length() == 0) {
            save();
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                assert header.equals("username,email,password,creation_time");

                String row;
                while ((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String username = String.valueOf(col[headers.get("username")]);
                    String email = String.valueOf(col[headers.get("email")]);
                    String password = String.valueOf(col[headers.get("password")]);
                    String creationTimeText = String.valueOf(col[headers.get("creation_time")]);
                    LocalDateTime ldt = LocalDateTime.parse(creationTimeText);
                    User user = userFactory.create(username, email, password, ldt);
                    accounts.put(username, user);
                }
            }
        }
    }

    /**
     * Saves the specified User and updates the CSV file.
     *
     * @param user the User object to save
     */
    @Override
    public void save(User user) {
        accounts.put(user.getName(), user);
        this.save();
    }

    /**
     * Retrieves all users from the data store.
     *
     * @return a list of all User objects
     */
    public List<User> getAllUsers() {
        return new ArrayList<>(accounts.values());
    }

    /**
     * Writes all user data to the CSV file, including the header and user details.
     */
    private void save() {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (User user : accounts.values()) {
                String line = "%s,%s,%s,%s".formatted(
                        user.getName(), user.getEmail(), user.getPassword(), user.getCreationTime());
                writer.write(line);
                writer.newLine();
            }

            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves a User by their username.
     *
     * @param username the username of the user to retrieve
     * @return the User object if found, null otherwise
     */
    public User get(String username) {
        return accounts.get(username);
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

    /**
     * Adds an event to the list of joined events for the specified user.
     *
     * @param eventname the name of the event
     * @param username  the username of the user
     */
    @Override
    public void addEvent(String eventname, String username) {
        if (existsByName(username)) {
            User registeredUser = accounts.get(username);
            registeredUser.getJoinedEvents().add(eventname);
            this.save();
        }
    }

}

