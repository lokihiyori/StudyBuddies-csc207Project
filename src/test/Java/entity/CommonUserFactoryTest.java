package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class CommonUserFactoryTest {

    private CommonUserFactory factory;

    @BeforeEach
    public void setUp() {
        factory = new CommonUserFactory();
    }

    @Test
    public void testCreate() {
        String name = "Test User";
        String password = "password123";
        String email = "test@example.com";
        LocalDateTime creationTime = LocalDateTime.now();

        User user = factory.create(name, password, email, creationTime);

        assertNotNull(user);
        assertEquals(name, user.getName());
        assertEquals(password, user.getPassword());
        assertEquals(email, user.getEmail());
        assertEquals(creationTime, ((CommonUser) user).getCreationTime());
    }
}
