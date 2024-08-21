package app;

import interface_adapter.LoginSignup.LoginSignupViewModel;
import interface_adapter.SignUp.SignUpViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import data_access.FileUserDataAccessObject;
import entity.UserFactory;
import view.SignupView;
import javax.swing.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class SignupUseCaseFactoryTest {

    @Mock private ViewManagerModel viewManagerModel;
    @Mock private LoginViewModel loginViewModel;
    @Mock private SignUpViewModel signupViewModel;
    @Mock private FileUserDataAccessObject userDataAccessObject;
    @Mock private LoginSignupViewModel loginSignupViewModel;
    @Mock private UserFactory userFactory;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateSignupViewSuccess() {
        // Setup the necessary environment and expectations
        when(userDataAccessObject.get(anyString())).thenReturn(null); // Simulate no user exists

        SignupView result = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject, loginSignupViewModel);

        assertNotNull(result, "SignupView should not be null when dependencies are correctly configured");
        // You might want to check for interactions with userDataAccessObject if there are any relevant
    }


}
