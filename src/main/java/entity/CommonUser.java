package entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a common user with a name, email, password, creation time, and a list of joined events and group chats.
 * Implements the {@link User} interface.
 */
public class CommonUser implements User{
    private final String name;
    private final LocalDateTime creationTime;
    private final String password;
    private List<String> events;
    private final String email;
    private final List<GroupChat> groupChatList;
    /**
     * Constructs a new {@link CommonUser} with the specified name, email, password, and creation time.
     *
     * @param name          The name of the user.
     * @param email         The email address of the user.
     * @param password      The password of the user.
     * @param creationTime  The time when the user account was created.
     */

    public CommonUser(String name, String email, String password, LocalDateTime creationTime) {
        this.name = name;
        this.password = password;
        this.creationTime = creationTime;
        this.events = new ArrayList<>();
        this.email = email;
        this.groupChatList = new ArrayList<>();;
    }

    /**
     * Returns the name of the user.
     *
     * @return The name of the user.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns the password of the user.
     *
     * @return The password of the user.
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Returns the email address of the user.
     *
     * @return The email address of the user.
     */
    @Override
    public String getEmail() {
        return email;
    }

    /**
     * Returns a list of event IDs that the user has joined.
     *
     * @return A list of joined event IDs.
     */
    public List<String>  getJoinedEvents(){return this.events;}

    /**
     * Returns a user object by name. Currently, this method returns null.
     *
     * @param users The name of the user to retrieve.
     * @return null (method not implemented).
     */
    @Override
    public User get(String users) {
        return null;
    }

    /**
     * Returns the list of group chats that the user is a part of.
     *
     * @return A list of {@link GroupChat} objects.
     */
    @Override
    public List<GroupChat> getGroupChatList() {
        return groupChatList;
    }

    /**
     * Returns the creation time of the user's account.
     *
     * @return The creation time of the account.
     */
    public LocalDateTime getCreationTime() {
        return creationTime;
    }
}

