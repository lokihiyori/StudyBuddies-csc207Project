package entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GroupChatFactoryTest {

    private GroupChatFactory factory;

    @BeforeEach
    public void setUp() {
        factory = new GroupChatFactory();
    }

    @Test
    public void testCreate() {
        String code = "CSC207";
        GroupChat groupChat = factory.create(code);

        Assertions.assertNotNull(groupChat); // Check that the GroupChat instance is not null
        assertEquals(code, groupChat.getCode()); // Verify the code is set correctly
    }
}
