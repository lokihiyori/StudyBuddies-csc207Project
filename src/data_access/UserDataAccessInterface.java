package data_access;

import entity.User;

public interface UserDataAccessInterface {
    void save(User user);

    boolean existsByName(String identifier);
}
