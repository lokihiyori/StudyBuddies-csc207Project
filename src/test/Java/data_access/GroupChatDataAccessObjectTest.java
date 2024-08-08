package data_access;

import entity.GroupChat;
import entity.GroupChatFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class GroupChatDataAccessObjectTest {
    private GroupChatDataAccessObject dao;
    private GroupChatFactory groupChatFactory;
    private final String csvFilePath = "test_groupchats.csv";

    @BeforeEach
    public void setUp() throws IOException {
        groupChatFactory = new GroupChatFactory();

        // Initialize DAO with a test CSV file
        dao = new GroupChatDataAccessObject(csvFilePath, groupChatFactory);
    }

    @Test
    public void testSaveGroupChat() throws IOException {
        GroupChat groupChat = groupChatFactory.create("GC101");
        dao.saveGroupChat(groupChat);

        GroupChat retrievedGroupChat = dao.getGroupChat("GC101");
        assertNotNull(retrievedGroupChat);
        assertEquals("GC101", retrievedGroupChat.getCode());
    }

    @Test
    public void testExistsByCode() throws IOException {
        GroupChat groupChat = groupChatFactory.create("GC101");
        dao.saveGroupChat(groupChat);

        assertTrue(dao.existsByCode("GC101"));
        assertFalse(dao.existsByCode("GC102"));
    }

    @Test
    public void testGetGroupChat() throws IOException {
        GroupChat groupChat = groupChatFactory.create("GC101");
        dao.saveGroupChat(groupChat);

        GroupChat retrievedGroupChat = dao.getGroupChat("GC101");
        assertNotNull(retrievedGroupChat);
        assertEquals("GC101", retrievedGroupChat.getCode());
    }

    @Test
    public void testSaveMultipleGroupChats() throws IOException {
        GroupChat groupChat1 = groupChatFactory.create("GC101");
        GroupChat groupChat2 = groupChatFactory.create("GC102");
        dao.saveGroupChat(groupChat1);
        dao.saveGroupChat(groupChat2);

        assertTrue(dao.existsByCode("GC101"));
        assertTrue(dao.existsByCode("GC102"));

        GroupChat retrievedGroupChat1 = dao.getGroupChat("GC101");
        GroupChat retrievedGroupChat2 = dao.getGroupChat("GC102");

        assertNotNull(retrievedGroupChat1);
        assertNotNull(retrievedGroupChat2);
        assertEquals("GC101", retrievedGroupChat1.getCode());
        assertEquals("GC102", retrievedGroupChat2.getCode());
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
