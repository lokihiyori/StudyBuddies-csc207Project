package app;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import data_access.FileEventDataAccessObject;
import data_access.FileUserDataAccessObject;
import entity.CommonCalendarEventFactory;
import entity.CommonUserFactory;
import interface_adapter.CreateEvent.CreateEventViewModel;
import interface_adapter.GoToCourse.CourseViewModel;
import interface_adapter.GroupChatViewModel;
import interface_adapter.LoginSignup.LoginSignupViewModel;
import interface_adapter.logged_In.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.SignUp.SignUpViewModel;
import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import view.CreateEventView;
import view.CourseView;
import view.LoginView;
import view.LoggedInView;
import view.SignupView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainTest {

    private FileUserDataAccessObject userDataAccessObject;
    private FileEventDataAccessObject eventDataAccessObject;
    private ViewManagerModel viewManagerModel;
    private JFrame application;

    @BeforeEach
    public void setUp() throws IOException {
        userDataAccessObject = mock(FileUserDataAccessObject.class);
        eventDataAccessObject = mock(FileEventDataAccessObject.class);
        viewManagerModel = new ViewManagerModel();

        // Mocking necessary components
        when(userDataAccessObject.get("testUser")).thenReturn(null);
        application = new JFrame();
    }

    @Test
    public void testMain() throws IOException {
        // Start the application
        SwingUtilities.invokeLater(() -> {
            try {
                Main.main(new String[]{});
            } catch (IOException e) {
                fail("Exception thrown during main execution: " + e.getMessage());
            }
        });

        // Since Swing is asynchronous, you may need to use a delay or synchronization
        try {
            Thread.sleep(1000); // Wait for the GUI to be initialized
        } catch (InterruptedException e) {
            fail("Test interrupted: " + e.getMessage());
        }

        // Verify that the application window is visible
        assertTrue(application.isVisible());

        // You can check for the presence of specific components
        JPanel views = (JPanel) application.getContentPane().getComponent(0);
        assertNotNull(views);
        assertEquals(CardLayout.class, views.getLayout().getClass());

        // Verify views are added
        Component[] components = views.getComponents();
        assertTrue(components.length > 0);

        // Check for specific views
        boolean hasSignupView = false;
        boolean hasLoginView = false;
        boolean hasLoggedInView = false;
        boolean hasCourseView = false;
        boolean hasCreateEventView = false;

        for (Component component : components) {
            if (component instanceof SignupView) {
                hasSignupView = true;
            } else if (component instanceof LoginView) {
                hasLoginView = true;
            } else if (component instanceof LoggedInView) {
                hasLoggedInView = true;
            } else if (component instanceof CourseView) {
                hasCourseView = true;
            } else if (component instanceof CreateEventView) {
                hasCreateEventView = true;
            }
        }

        assertTrue(hasSignupView);
        assertTrue(hasLoginView);
        assertTrue(hasLoggedInView);
        assertTrue(hasCourseView);
        assertTrue(hasCreateEventView);

        // Optionally: Test if the initial view is set correctly
        assertEquals("signupView", viewManagerModel.getActiveView());
    }
}
