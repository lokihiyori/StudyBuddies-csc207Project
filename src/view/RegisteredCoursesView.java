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

    public static void main(String[] args) {
        JFrame frame = new JFrame("Registered Courses");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        RegisteredCoursesView registeredCoursesView = new RegisteredCoursesView();
        frame.add(registeredCoursesView);
        frame.setVisible(true);
    }
}
