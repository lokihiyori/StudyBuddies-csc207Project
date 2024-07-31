package view;

import javax.swing.*;
import java.awt.*;
import interface_adapter.RegisteredCoursesViewModel;

public class RegisteredCoursesView extends JPanel {
    public RegisteredCoursesView(RegisteredCoursesViewModel viewModel) {
        JLabel title = new JLabel("Registered Group Chats");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextArea registeredCoursesArea = new JTextArea(10, 30);
        registeredCoursesArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(registeredCoursesArea);

        // Use the ViewModel to get the data
        registeredCoursesArea.setText(viewModel.getFormattedGroupChatCodes());

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(scrollPane);
    }
}
