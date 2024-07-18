package data_access;

import entity.User;

public interface UserSignUpDataAccessInterface {
    boolean existsByName(String identifier);

    void save(User user);
}
