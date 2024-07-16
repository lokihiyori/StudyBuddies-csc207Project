package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CourseSearchView extends JPanel implements ActionListener {

    private final JTextField courseSearchField;
    private final JButton searchButton;
    private final JLabel resultLabel;

    /**
     * A window with a title and a JButton.
     */
    public CourseSearchView() {
        JLabel title = new JLabel("Course Search");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        courseSearchField = new JTextField(20);
        searchButton = new JButton("Search");
        resultLabel = new JLabel("");

        // Ensure text fields have visible font color
        courseSearchField.setForeground(Color.BLACK);
        courseSearchField.setBackground(Color.WHITE);

        JPanel buttons = new JPanel();
        buttons.add(searchButton);

        searchButton.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(new LabelTextPanel(new JLabel("Course"), courseSearchField));
        this.add(buttons);
        this.add(resultLabel);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        if (evt.getSource() == searchButton) {
            String course = courseSearchField.getText();
            resultLabel.setText("");

            if (course.isEmpty()) {
                resultLabel.setText("Course cannot be empty.");
            } else {
                // Simulate searching for the course
                boolean courseFound = searchForCourse(course);

                if (courseFound) {
                    resultLabel.setText("Course found: " + course);
                } else {
                    resultLabel.setText("Course not found!");
                }
            }
        }
    }

    /**
     * Simulates searching for a course.
     * Replace this method with your actual search logic.
     */


    private boolean searchForCourse(String course) {
        // Simulate course search logic
        // Replace this with actual search logic (e.g., database query)
        // For demonstration, let's assume the course is found if it contains "Java"
        return course.contains("CSC207");

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Course Search");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);

        CourseSearchView courseSearchView = new CourseSearchView();
        frame.add(courseSearchView);
        frame.setVisible(true);
    }
}



