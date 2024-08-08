package interface_adapter;

import interface_adapter.SignUp.SignUpState;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SignUpStateTest {

    @Test
    void testDefaultConstructor() {
        SignUpState signUpState = new SignUpState();

        assertEquals("", signUpState.getUsername());
        assertNull(signUpState.getUsernameError());
        assertEquals("", signUpState.getEmail());
        assertNull(signUpState.getEmailError());
        assertEquals("", signUpState.getPassword());
        assertNull(signUpState.getPasswordError());
        assertEquals("", signUpState.getRepeatPassword());
        assertNull(signUpState.getRepeatPasswordError());
    }

    @Test
    void testSetAndGetUsername() {
        SignUpState signUpState = new SignUpState();
        signUpState.setUsername("testUser");

        assertEquals("testUser", signUpState.getUsername());
    }

    @Test
    void testSetAndGetUsernameError() {
        SignUpState signUpState = new SignUpState();
        signUpState.setUsernameError("username error");

        assertEquals("username error", signUpState.getUsernameError());
    }

    @Test
    void testSetAndGetEmail() {
        SignUpState signUpState = new SignUpState();
        signUpState.setEmail("test@example.com");

        assertEquals("test@example.com", signUpState.getEmail());
    }

    @Test
    void testSetAndGetEmailError() {
        SignUpState signUpState = new SignUpState();
        signUpState.setEmailError("email error");

        assertEquals("email error", signUpState.getEmailError());
    }

    @Test
    void testSetAndGetPassword() {
        SignUpState signUpState = new SignUpState();
        signUpState.setPassword("password123");

        assertEquals("password123", signUpState.getPassword());
    }

    @Test
    void testSetAndGetPasswordError() {
        SignUpState signUpState = new SignUpState();
        signUpState.setPasswordError("password error");

        assertEquals("password error", signUpState.getPasswordError());
    }

    @Test
    void testSetAndGetRepeatPassword() {
        SignUpState signUpState = new SignUpState();
        signUpState.setRepeatPassword("password123");

        assertEquals("password123", signUpState.getRepeatPassword());
    }

    @Test
    void testSetAndGetRepeatPasswordError() {
        SignUpState signUpState = new SignUpState();
        signUpState.setRepeatPasswordError("repeat password error");

        assertEquals("repeat password error", signUpState.getRepeatPasswordError());
    }

    @Test
    void testCopyConstructor() {
        SignUpState original = new SignUpState();
        original.setUsername("originalUser");
        original.setUsernameError("original username error");
        original.setEmail("original@example.com");
        original.setEmailError("original email error");
        original.setPassword("originalPassword");
        original.setPasswordError("original password error");
        original.setRepeatPassword("originalRepeatPassword");
        original.setRepeatPasswordError("original repeat password error");

        SignUpState copy = new SignUpState(original);

        assertEquals("originalUser", copy.getUsername());
        assertEquals("original username error", copy.getUsernameError());
        assertEquals("original@example.com", copy.getEmail());
        assertEquals("original email error", copy.getEmailError());
        assertEquals("originalPassword", copy.getPassword());
        assertEquals("original password error", copy.getPasswordError());
        assertEquals("originalRepeatPassword", copy.getRepeatPassword());
        assertEquals("original repeat password error", copy.getRepeatPasswordError());
    }
}
