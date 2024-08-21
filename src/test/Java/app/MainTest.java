package app;
import data_access.*;

import entity.CommonUserFactory;
import interface_adapter.ViewManagerModel;

import org.junit.jupiter.api.*;
import org.mockito.*;
import view.SignupView;

import javax.swing.*;
import java.awt.*;
import java.io.*;

import static org.mockito.Mockito.*;

public class MainTest {
    private AutoCloseable mocks;

    @BeforeEach
    public void setup() {
        mocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void tearDown() throws Exception {
        mocks.close();
    }


    @Test
    public void testIOExceptionHandlingInUserDataAccess() {
        // Simulate IOException in user data access object creation
        try (MockedConstruction<FileUserDataAccessObject> mockedFileUserDataAccessObject = mockConstruction(
                FileUserDataAccessObject.class, (mock, context) -> {
                    when(mock.toString()).thenThrow(new IOException());
                })) {

            Assertions.assertThrows(RuntimeException.class, () -> Main.main(new String[]{}));
        }
    }
}
