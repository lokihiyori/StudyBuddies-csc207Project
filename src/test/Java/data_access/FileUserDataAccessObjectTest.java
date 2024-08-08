package data_access;

import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

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

    // Clean up the test CSV file after tests
    @AfterEach
    public void tearDown() {
        File file = new File(csvFilePath);
        if (file.exists()) {
            file.delete();
        }
    }
}
