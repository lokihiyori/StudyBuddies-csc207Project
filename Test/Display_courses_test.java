import view1.RegisteredCoursesView;

import javax.swing.*;

public class Display_courses_test {
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
