package view;

import javax.swing.*;
import java.awt.*;

public class RegisteredCoursesView extends JPanel {

    public RegisteredCoursesView() {
        JLabel title = new JLabel("Registered Courses");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextArea registeredCoursesArea = new JTextArea(10, 30);
        registeredCoursesArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(registeredCoursesArea);

        // Sample data, you should replace it with actual registered courses data
        registeredCoursesArea.setText("Course 1\nCourse 2\nCourse 3");

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(scrollPane);
    }


}
