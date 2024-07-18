package usecase.SignUp;

import entity.User;

public interface SignUpDataAccessInterface {

    User get(String username);

    boolean existsByName(String username);

    void save(User user);
}
