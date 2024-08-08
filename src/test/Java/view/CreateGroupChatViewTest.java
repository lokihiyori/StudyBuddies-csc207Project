package view;

import interface_adapter.CreateGroupChat.CreateGroupChatController;
import interface_adapter.CreateGroupChat.CreateGroupChatState;
import interface_adapter.CreateGroupChat.CreateGroupChatViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CreateGroupChatViewTest {

    private CreateGroupChatViewModel mockViewModel;
    private CreateGroupChatController mockController;
    private CreateGroupChatView view;

    @BeforeEach
    public void setUp() {
        mockViewModel = mock(CreateGroupChatViewModel.class);
        mockController = mock(CreateGroupChatController.class);
        view = new CreateGroupChatView(mockViewModel, mockController);
        view.setVisible(false);  // Prevent the UI from actually displaying during tests
    }

    @Test
    public void testInitializeUI() {
        // Check if the frame is correctly set up
        assertEquals("Create Group Chat", view.getTitle());
        assertEquals(400, view.getWidth());
        assertEquals(300, view.getHeight());
        assertEquals(JFrame.EXIT_ON_CLOSE, view.getDefaultCloseOperation());

        // Check if components are correctly added to the frame
        Container contentPane = view.getContentPane();
        assertEquals(BorderLayout.class, contentPane.getLayout().getClass());

        Component[] components = contentPane.getComponents();
        assertEquals(2, components.length);

        // Check input panel components
        JPanel inputPanel = (JPanel) components[0];
        assertEquals(GridLayout.class, inputPanel.getLayout().getClass());
        assertEquals(3, inputPanel.getComponentCount());

        JLabel codeLabel = (JLabel) inputPanel.getComponent(0);
        assertEquals("Code:", codeLabel.getText());

        JTextField codeTextField = (JTextField) inputPanel.getComponent(1);
        assertNotNull(codeTextField);

        JButton createButton = (JButton) inputPanel.getComponent(2);
        assertEquals("Create Group Chat", createButton.getText());

        // Check status label
        JLabel statusLabel = (JLabel) components[1];
        assertEquals("Status: ", statusLabel.getText());
    }



    @Test
    public void testPropertyChangeSuccess() throws NoSuchFieldException, IllegalAccessException {
        // Access the private statusLabel field
        Field statusLabelField = CreateGroupChatView.class.getDeclaredField("statusLabel");
        statusLabelField.setAccessible(true);
        JLabel statusLabel = (JLabel) statusLabelField.get(view);

        CreateGroupChatState state = new CreateGroupChatState();
        state.setCode("testCode");

        when(mockViewModel.getState()).thenReturn(state);

        // Simulate property change
        view.propertyChange(new PropertyChangeEvent(mockViewModel, "state", null, state));

        assertEquals("Status: Group chat created with code testCode", statusLabel.getText());
    }

    @Test
    public void testPropertyChangeFailure() throws NoSuchFieldException, IllegalAccessException {
        // Access the private statusLabel field
        Field statusLabelField = CreateGroupChatView.class.getDeclaredField("statusLabel");
        statusLabelField.setAccessible(true);
        JLabel statusLabel = (JLabel) statusLabelField.get(view);

        CreateGroupChatState state = new CreateGroupChatState();
        state.setCode(null);

        when(mockViewModel.getState()).thenReturn(state);

        // Simulate property change
        view.propertyChange(new PropertyChangeEvent(mockViewModel, "state", null, state));

        assertEquals("Status: Failed to create group chat.", statusLabel.getText());
    }
}
