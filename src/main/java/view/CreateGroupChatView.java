package view;

import interface_adapter.CreateGroupChat.CreateGroupChatController;
import interface_adapter.CreateGroupChat.CreateGroupChatState;
import interface_adapter.CreateGroupChat.CreateGroupChatViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

/**
 * CreateGroupChatView is a JFrame that provides a user interface for creating group chats.
 */
public class CreateGroupChatView extends JFrame implements PropertyChangeListener {
    private final CreateGroupChatViewModel viewModel;
    private final CreateGroupChatController controller;

    private JTextField codeTextField;
    private JTextArea usersListTextArea;
    private JLabel statusLabel;

    /**
     * Constructs a new CreateGroupChatView with the specified view model and controller.
     *
     * @param viewModel   the CreateGroupChatViewModel instance
     * @param controller  the CreateGroupChatController instance
     */
    public CreateGroupChatView(CreateGroupChatViewModel viewModel,CreateGroupChatController controller){
        this.viewModel = viewModel;
        this.controller = controller;

        viewModel.addPropertyChangeListener(this);
        initializeUI();
    }

    /**
     * Initializes the user interface components.
     */
    private void initializeUI() {
        setTitle("Create Group Chat");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Code:"));
        codeTextField = new JTextField();
        inputPanel.add(codeTextField);

        JButton createButton = new JButton("Create Group Chat");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String code = codeTextField.getText();
                try {
                    controller.executeCreateGroupChat(code);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        inputPanel.add(createButton);

        add(inputPanel, BorderLayout.CENTER);

        statusLabel = new JLabel("Status: ");
        add(statusLabel, BorderLayout.SOUTH);
    }

    /**
     * Handles property changes in the view model.
     *
     * @param evt the PropertyChangeEvent object
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            CreateGroupChatState state = viewModel.getState();
            // Update the UI based on the new state
            if (state.getCode() != null) {
                statusLabel.setText("Status: Group chat created with code " + state.getCode());
            } else {
                statusLabel.setText("Status: Failed to create group chat.");
            }
        }

    }
}
