package use_case;

import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.Signup.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class SignupInteractorTest {

    private String username = "testUser";
    private String email = "test@example.com";
    private String password = "password";
    private String repeatPassword = "password";
    private SignupInputData signupInputData;

    private SignupUserDataAccessInterface userDataAccessObject;
    private SignupOutputBoundary presenter;
    private CommonUserFactory userFactory;

    @BeforeEach
    void setUp() {
        signupInputData = new SignupInputData(username, email, password, repeatPassword);
        userFactory = new CommonUserFactory();
        userDataAccessObject = new SignupUserDataAccessInterface() {
            private Map<String, User> users = new HashMap<>();

            @Override
            public boolean existsByName(String identifier) {
                return users.containsKey(identifier);
            }

            @Override
            public void save(User user) {
                users.put(user.getName(), user);
            }
        };
        presenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                assertEquals(username, user.getUsername());
                assertEquals(email, user.getEmail());
                assertNotNull(user.getCreationTime());
                assertFalse(user.useCaseFailed);
            }

            @Override
            public void prepareFailView(String error) {
                // You can add assertions here if needed to test failure scenarios
            }
        };
    }

    @Test
    void testExecute_Success() {
        SignupInputBoundary interactor = new SignupInteractor(userDataAccessObject, presenter, userFactory);
        interactor.execute(signupInputData);
    }

    @Test
    void testUserCreation() {
        UserFactory factory = new CommonUserFactory();
        LocalDateTime now = LocalDateTime.now();
        User user = factory.create("testUser", "test@example.com", "password", now);

        assertEquals("testUser", user.getName());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("password", user.getPassword());
        assertEquals(now, user.getCreationTime());
    }

    @Test
    void testExecute_UserAlreadyExists() {
        userDataAccessObject.save(userFactory.create(username, email, password, LocalDateTime.now()));
        presenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                // This should not be called
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("User already exists.", error);
            }
        };
        SignupInputBoundary interactor = new SignupInteractor(userDataAccessObject, presenter, userFactory);
        interactor.execute(signupInputData);
    }

    @Test
    void testExecute_PasswordsDoNotMatch() {
        signupInputData = new SignupInputData(username, email, password, "differentPassword");
        presenter = new SignupOutputBoundary() {
            @Override
            public void prepareSuccessView(SignupOutputData user) {
                // This should not be called
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Passwords don't match.", error);
            }
        };
        SignupInputBoundary interactor = new SignupInteractor(userDataAccessObject, presenter, userFactory);
        interactor.execute(signupInputData);
    }
}