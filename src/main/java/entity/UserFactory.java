package entity;

import java.time.LocalDateTime;
import java.util.List;
/**
 * A factory interface for creating instances of {@link User}.
 * Implementations of this interface are responsible for providing concrete
 * methods to create new user instances.
 */

public interface UserFactory {
    /**
     * Creates a new {@link User} with the specified details.
     * The implementation must ensure that the provided password is valid.
     *
     * @param name       the name of the user
     * @param email      the email address of the user
     * @param password   the password for the user (must be valid)
     * @param ltd        the creation time of the user's account
     * @return           a new instance of {@link User} with the specified details
     */
    User create(String name, String email, String password, LocalDateTime ltd);

}
