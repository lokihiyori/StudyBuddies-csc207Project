package view;

import entity.Course;
import interface_adapter.GoToCourse.CourseState;
import interface_adapter.GoToCourse.CourseViewController;
import interface_adapter.GoToCourse.CourseViewModel;
import interface_adapter.SignUp.SignUpState;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CourseView extends JPanel implements PropertyChangeListener {

    private final CourseViewModel courseViewModel;
    private final CourseViewController courseViewController;
    public final String viewName = "CourseView";
    private JComboBox<String> courseComboBox;
    private JComboBox<String> eventComboBox;
    private JLabel usernameLabel;
    private JButton profileButton;
    private JButton joinGroupChatsButton;
    private JButton logOutButton;
    private JButton createEventButton;
    private JButton addCalendarButton;

    public CourseView(CourseViewModel courseViewModel, CourseViewController courseViewController){
        this.courseViewModel = courseViewModel;
        this.courseViewModel.addPropertyChangeListener(this);
        this.courseViewController = courseViewController;
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(new Color(204, 255, 255));  // Set the entire panel's background color

        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(41, 128, 185));
        JLabel title = new JLabel("StudyBuddies");
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setForeground(Color.WHITE);
        topPanel.add(title);
        add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerPanel.setOpaque(false);  // Make the center panel transparent
        usernameLabel = new JLabel("Welcome: ");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        centerPanel.add(usernameLabel);
        add(centerPanel, BorderLayout.CENTER);

        List<String> courseNames = readCoursesFromFile("courses.csv");
        courseComboBox = new JComboBox<>(courseNames.toArray(new String[0]));
        centerPanel.add(courseComboBox);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        eventComboBox = new JComboBox<>();
        eventComboBox.setFont(new Font("Arial", Font.BOLD, 18));
        eventComboBox.addItem("Choose your Event");
        centerPanel.add(eventComboBox);

        addCalendarButton = new JButton("Add Calendar");
        addCalendarButton.addActionListener(e -> handleAddCalendarAction());
        centerPanel.add(addCalendarButton);
        add(centerPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false);  // Make the button panel transparent
        profileButton = new JButton("Profile");
        profileButton.setBackground(new Color(153, 255, 153));
        profileButton.addActionListener(e -> handleProfileAction());
        buttonPanel.add(profileButton);
        joinGroupChatsButton = new JButton("Group Chat");
        joinGroupChatsButton.setBackground(new Color(255, 113, 4));
        joinGroupChatsButton.addActionListener(e -> handleJoinGroupChatsAction());
        buttonPanel.add(joinGroupChatsButton);
        createEventButton = new JButton("CreateEvent");
        createEventButton.setBackground(new Color(255, 255, 0));
        createEventButton.addActionListener(e -> handleJoinGroupChatsAction());
        buttonPanel.add(createEventButton);
        logOutButton = new JButton("Log Out");
        logOutButton.setBackground(new Color(192, 57, 43));
        logOutButton.setForeground(Color.WHITE);
        logOutButton.setMinimumSize(new Dimension(90, 50));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        buttonPanel.add(logOutButton, gbc);
        add(buttonPanel, BorderLayout.SOUTH);

        logOutButton.addActionListener(e -> handleLogOut());
    }


    private void handleLogOut() {
        courseViewController.excuteLogOut();
    }

    private void handleProfileAction() {
        // Implement the action for the Profile button
    }

    private void handleAddCalendarAction() {
        // Implement the action for the Add Calendar button
    }

    private void handleJoinGroupChatsAction() {
        // Implement the action for the Join Group Chats button
    }

    private void updateUsernameLabel() {
        CourseState state = courseViewModel.getState();
        usernameLabel.setText("Currently logged in: " + state.getUsername());
    }
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            updateUsernameLabel();
        }
    }

    public List<String> readCoursesFromFile(String filePath) {
        List<String> courses = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Skip the header line
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                courses.add(values[0]);  // Assuming the course name is the first element
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return courses;
    }
   }

