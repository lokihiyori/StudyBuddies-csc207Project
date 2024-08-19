package use_case.Signup;

import entity.User;

/**
 * The SignupUserDataAccessInterface interface defines the methods for accessing and managing user data
 * within the context of the signup process.
 */
public interface SignupUserDataAccessInterface {

    /**
     * Checks if a user with the specified identifier already exists in the data store.
     *
     * @param identifier the identifier (usually username) to check for existence.
     * @return true if a user with the specified identifier exists; false otherwise.
     */
    boolean existsByName(String identifier);

    /**
     * Saves the provided user to the data store.
     *
     * @param user the user to be saved.
     */
    void save(User user);

}
