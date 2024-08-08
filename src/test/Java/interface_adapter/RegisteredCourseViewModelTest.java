package interface_adapter;

import entity.CommonUser;
import entity.GroupChat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RegisteredCoursesViewModelTest {

    private RegisteredCoursesViewModel viewModel;
    private CommonUser user;

    @BeforeEach
    void setUp() {
        // Create a CommonUser with group chats
        user = new CommonUser("testUser", "testUser@example.com", "password", LocalDateTime.now());
        GroupChat groupChat1 = new GroupChat("CSC207");
        GroupChat groupChat2 = new GroupChat("CSC236");
        user.getGroupChatList().addAll(Arrays.asList(groupChat1, groupChat2));

        viewModel = new RegisteredCoursesViewModel(user);
    }

    @Test
    void testGetFormattedGroupChatCodes() {
        // Act
        String formattedCodes = viewModel.getFormattedGroupChatCodes();

        // Assert
        assertEquals("CSC207\nCSC236", formattedCodes);
    }
}
