package entity;

import java.time.LocalDateTime;
import java.util.List;

public interface UserFactory {
    /** Requires: password is valid. */
    User create(String name, String email, String password, LocalDateTime ltd);

}
