package data_access;

import entity.CommonUserFactory;
import entity.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileUserDataAccessObjectTest {
    private FileUserDataAccessObject dao;
    private CommonUserFactory commonUserFactory;
    private final String csvFilePath = "test_users.csv";

    @BeforeEach
    public void setUp() throws IOException {
        commonUserFactory = new CommonUserFactory();

        // Initialize DAO with a test CSV file
        dao = new FileUserDataAccessObject(csvFilePath, commonUserFactory);
    }

    @Test
    public void testSaveUser() {
        User user = commonUserFactory.create("testuser", "testuser@example.com", "password123", LocalDateTime.now());
        dao.save(user);

        User retrievedUser = dao.get("testuser");
        assertNotNull(retrievedUser);
        assertEquals("testuser", retrievedUser.getName());
        assertEquals("password123", retrievedUser.getPassword());
        assertEquals("testuser@example.com", retrievedUser.getEmail());
    }

    @Test
    public void testExistsByName() {
        User user = commonUserFactory.create("testuser", "testuser@example.com", "password123", LocalDateTime.now());
        dao.save(user);

        assertTrue(dao.existsByName("testuser"));
        assertFalse(dao.existsByName("nonexistentuser"));
    }

    @Test
    public void testGet() {
        User user = commonUserFactory.create("testuser", "testuser@example.com", "password123", LocalDateTime.now());
        dao.save(user);

        User retrievedUser = dao.get("testuser");
        assertNotNull(retrievedUser);
        assertEquals("testuser", retrievedUser.getName());
        assertEquals("password123", retrievedUser.getPassword());
        assertEquals("testuser@example.com", retrievedUser.getEmail());
    }

    @Test
    public void testUpdateUser() {
        User user = commonUserFactory.create("testuser", "testuser@example.com", "password123", LocalDateTime.now());
        dao.save(user);

        User updatedUser = commonUserFactory.create("testuser", "newemail@example.com", "newpassword123", LocalDateTime.now());
        dao.save(updatedUser);

        User retrievedUser = dao.get("testuser");
        assertNotNull(retrievedUser);
        assertEquals("newemail@example.com", retrievedUser.getEmail());
        assertEquals("newpassword123", retrievedUser.getPassword());
    }

    @Test
    public void testGetAllUsers() {
        User user1 = commonUserFactory.create("testuser1", "testuser1@example.com", "password123", LocalDateTime.now());
        User user2 = commonUserFactory.create("testuser2", "testuser2@example.com", "password123", LocalDateTime.now());
        dao.save(user1);
        dao.save(user2);

        List<User> users = dao.getAllUsers();
        assertEquals(2, users.size());
        assertTrue(users.stream().anyMatch(u -> u.getName().equals("testuser1")));
        assertTrue(users.stream().anyMatch(u -> u.getName().equals("testuser2")));
    }

    // Clean up the test CSV file after tests
    @AfterEach
    public void tearDown() {
        File file = new File(csvFilePath);
        if (file.exists()) {
            file.delete();
        }
    }
}

