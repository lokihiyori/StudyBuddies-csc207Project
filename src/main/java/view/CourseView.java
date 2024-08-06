package view;

import data_access.FileUserDataAccessObject;
import entity.Course;
import interface_adapter.CreateEvent.CreateEventController;
import interface_adapter.CreateEvent.CreateEventViewModel;
import interface_adapter.GoToCourse.CourseState;
import interface_adapter.GoToCourse.CourseViewController;
import interface_adapter.GoToCourse.CourseViewModel;
import interface_adapter.SignUp.SignUpState;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_In.LoggedInViewModel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    private JList<String> eventsList;
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

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);  // Make the center panel transparent
        usernameLabel = new JLabel("Welcome: ");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        centerPanel.add(usernameLabel);
        add(centerPanel, BorderLayout.CENTER);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        List<String> courseNames = readCoursesFromFile("courses.csv");
        courseComboBox = new JComboBox<>(courseNames.toArray(new String[0]));
        courseComboBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, courseComboBox.getPreferredSize().height));
        centerPanel.add(courseComboBox);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        eventComboBox = new JComboBox<>();
        eventComboBox.setFont(new Font("Arial", Font.BOLD, 18));
        eventComboBox.addItem("Choose your Event");
        eventComboBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, eventComboBox.getPreferredSize().height));
        centerPanel.add(eventComboBox);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        addCalendarButton = new JButton("Add Calendar");
        addCalendarButton.setAlignmentX(Component.CENTER_ALIGNMENT);
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
        createEventButton.addActionListener(e -> handleCreateEventAction());
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

    private void handleJoinGroupChatsAction() {
    }


    private void handleLogOut() {
        courseViewController.executeLogOut();
    }

    private void handleProfileAction() {
        // Implement the action for the Profile button
    }

    private void handleAddCalendarAction() {
        // Implement the action for the Add Calendar button
    }

    private void handleCreateEventAction()  {
        CourseState courseState = courseViewModel.getState();
        courseViewController.executeCreateEvent(courseState.getUsername());
//        updateEventsList();
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
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                courses.add(values[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return courses;
    }
//    private void updateEventsList() {
//        try {
//            List<String> events = loadEventsFromCsvFile("events.csv");
//            eventsList.setListData(events.toArray(new String[0]));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    private List<String> loadEventsFromCsvFile(String fileName) throws IOException {
        List<String> events = new ArrayList<>();
        Path path = Paths.get(fileName);
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Assuming the first column in CSV contains the event name
                String[] data = line.split(",");
                if (data.length > 0) {
                    events.add(data[0]);  // Add the first column (event name) to the list
                }
            }
        }
        return events;

}}

