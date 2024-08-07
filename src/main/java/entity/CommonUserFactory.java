package entity;

import java.time.LocalDateTime;
import java.util.List;

public class CommonUserFactory implements UserFactory {
    /**
     * Requires: password is valid.
     * @param name
     * @param password
     * @return
     */

    @Override
    public User create(String name, String email, String password, LocalDateTime ltd) {
        return new CommonUser(name, email, password, ltd);
    }
}
