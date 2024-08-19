package entity;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents a user in the system. This interface provides methods to access
 * user information, such as their name, email, password, joined events, and group chats.
 * Implementing classes should provide concrete implementations for managing user details.
 */
public interface User {

    /**
     * Returns the name of the user.
     *
     * @return the name of the user
     */
    String getName();

    /**
     * Returns the creation time of the user's account.
     *
     * @return the creation time of the user's account
     */
    LocalDateTime getCreationTime();

    /**
     * Returns the password of the user. This method may return a hashed or encrypted password.
     *
     * @return the password of the user
     */
    String getPassword();

    /**
     * Returns a list of events that the user has joined.
     *
     * @return a list of joined events
     */
    List<String> getJoinedEvents();

    /**
     * Returns the email address of the user.
     *
     * @return the email address of the user
     */
    String getEmail();

    /**
     * Retrieves a user based on their identifier. The specific implementation of how the user is retrieved
     * depends on the underlying system or data source.
     *
     * @param users the identifier or criteria to find the user
     * @return the user matching the given identifier or criteria, or null if not found
     */
    User get(String users);
    /**
     * Returns a list of group chats that the user is a member of.
     *
     * @return a list of group chats associated with the user
     */
    List<GroupChat> getGroupChatList();

}

