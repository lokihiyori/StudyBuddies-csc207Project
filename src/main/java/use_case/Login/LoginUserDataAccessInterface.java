package use_case.Login;

import entity.User;

/**
 * The LoginUserDataAccessInterface defines the methods required for interacting
 * with the data storage system related to user login. Implementations of this
 * interface are responsible for checking user existence, saving user data, and
 * retrieving user information from the system.
 */
public interface LoginUserDataAccessInterface {

    /**
     * Checks if a user with the specified identifier (username) exists in the data storage system.
     *
     * @param identifier the username of the user to check for existence.
     * @return true if a user with the given username exists, false otherwise.
     */
    boolean existsByName(String identifier);

    /**
     * Saves the specified user to the data storage system.
     * Implementations should handle the logic for persisting user information.
     *
     * @param user the User object to be saved.
     */
    void save(User user);

    /**
     * Retrieves the user associated with the specified username from the data storage system.
     *
     * @param username the username of the user to retrieve.
     * @return the User object associated with the given username.
     */
    User get(String username);
}
