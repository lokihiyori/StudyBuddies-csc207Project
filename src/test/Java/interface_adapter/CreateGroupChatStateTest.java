package interface_adapter;

import entity.CommonUser;
import interface_adapter.CreateGroupChat.CreateGroupChatState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CreateGroupChatStateTest {

    private CreateGroupChatState state;

    @BeforeEach
    void setUp() {
        state = new CreateGroupChatState();
    }

    @Test
    void testInitialState() {
        // Test initial state of a newly created object
        assertNull(state.getCode(), "Code should be null initially");
        assertNull(state.getUsersList(), "Users list should be null initially");
    }

    @Test
    void testSettersAndGetters() {
        // Setup a list of CommonUser
        CommonUser user1 = new CommonUser("Alice", "alice@example.com", "password1", java.time.LocalDateTime.now());
        CommonUser user2 = new CommonUser("Bob", "bob@example.com", "password2", java.time.LocalDateTime.now());
        List<CommonUser> users = Arrays.asList(user1, user2);

        // Set properties
        state.setCode("12345");
        state.setUsersList(users);

        // Assertions to verify the correctness of getters and setters
        assertEquals("12345", state.getCode(), "The code should match the set value");
        assertEquals(users, state.getUsersList(), "The user list should match the set value");
        assertTrue(state.getUsersList().contains(user1), "The user list should contain user1");
        assertTrue(state.getUsersList().contains(user2), "The user list should contain user2");
    }

    @Test
    void testCopyConstructor() {
        // Prepare the original state with some users
        CommonUser user1 = new CommonUser("Alice", "alice@example.com", "securePass1", java.time.LocalDateTime.now());
        CommonUser user2 = new CommonUser("Bob", "bob@example.com", "securePass2", java.time.LocalDateTime.now());
        CreateGroupChatState originalState = new CreateGroupChatState();
        originalState.setCode("Chat001");
        originalState.setUsersList(Arrays.asList(user1, user2));

        // Create a new state using the copy constructor
        CreateGroupChatState copiedState = new CreateGroupChatState(originalState);

        // Assert that the 'code' is copied correctly
        assertEquals(originalState.getCode(), copiedState.getCode(), "The codes should be identical.");

        // Since the implementation is a shallow copy, we expect the same list object
        assertSame(originalState.getUsersList(), copiedState.getUsersList(), "The lists should be the same object due to shallow copying.");

        // Also verify that the elements in the list are the same (because it is a shallow copy)
        assertSame(originalState.getUsersList().get(0), copiedState.getUsersList().get(0), "The user objects should be the same (shallow copy).");
        assertSame(originalState.getUsersList().get(1), copiedState.getUsersList().get(1), "The user objects should be the same (shallow copy).");
    }
}