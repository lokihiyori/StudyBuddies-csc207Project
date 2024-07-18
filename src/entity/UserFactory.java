package entity;
import java.util.List;

public class UserFactory implements UserFactoryInterface {
    /**
     * Requires: password is valid.
     * @param name
     * @param password
     * @return
     */

    @Override
    public User createUser(String name, String password, String username, String email, List<String> courses) {
        return new Users(name, password, username, email, courses);
    }
}
