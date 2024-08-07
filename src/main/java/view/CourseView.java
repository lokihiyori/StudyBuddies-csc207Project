package view;
import data_access.CourseDataAccessObject;
import data_access.FileUserDataAccessObject;
import SocketIO.GroupChatClient;
import SocketIO.GroupChatPort;
import SocketIO.GroupChatServer;

import entity.*;

import interface_adapter.CreateEvent.CreateEventViewModel;
import interface_adapter.GoToCourse.CourseState;
import interface_adapter.GoToCourse.CourseViewController;
import interface_adapter.GoToCourse.CourseViewModel;
import interface_adapter.SignUp.SignUpState;
import interface_adapter.UserProfile.UserProfileController;
import interface_adapter.UserProfile.UserProfilePresenter;
import interface_adapter.UserProfile.UserProfileState;
import interface_adapter.UserProfile.UserProfileViewModel;
import use_case.UserProfile.UserProfileInteractor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;
import java.util.stream.Collectors;

public class CourseView extends JPanel implements PropertyChangeListener {

    private final CourseViewModel courseViewModel;
    private final CreateEventViewModel createEventViewModel;
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

    public CourseView(CourseViewModel courseViewModel, CourseViewController courseViewController, CreateEventViewModel createEventViewModel){
        this.courseViewModel = courseViewModel;
        this.courseViewModel.addPropertyChangeListener(this);
        this.courseViewController = courseViewController;
        this.createEventViewModel = createEventViewModel;
        createEventViewModel.addPropertyChangeListener(this);
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
        List<String> eventNames = readEventsFromFile("events.csv");
        eventComboBox.setFont(new Font("Arial", Font.BOLD, 18));
        eventComboBox.addItem("Choose your Event");
        for (String eventName : eventNames) {
            eventComboBox.addItem(eventName);}
        eventComboBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, eventComboBox.getPreferredSize().height));
        centerPanel.add(eventComboBox);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 5)));

        addCalendarButton = new JButton("My Event");
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


    private void handleLogOut() {
        courseViewController.executeLogOut();
    }

    private void handleProfileAction() {
        try {
            // Initialize FileUserDataAccessObject
            String userCsvPath = "user.csv"; // Ensure this path is correct
            FileUserDataAccessObject userDataAccessObject = new FileUserDataAccessObject(userCsvPath, new CommonUserFactory());

            // Retrieve user data
            User user = userDataAccessObject.get("ah"); // Replace with actual username
            if (user == null) {
                throw new RuntimeException("User not found.");
            }

            // Initialize CourseDataAccessObject
            String courseCsvPath = "courses.csv"; // Ensure this path is correct
            CourseFactory courseFactory = new CourseFactory(); // Ensure this class exists and is correctly implemented
            GroupChatFactory groupChatFactory = new GroupChatFactory(); // Ensure this class exists and is correctly implemented
            CourseDataAccessObject courseDataAccessObject = new CourseDataAccessObject(courseCsvPath, courseFactory, groupChatFactory);

            // Initialize UserProfile components
            UserProfileViewModel userProfileViewModel = new UserProfileViewModel();
            UserProfilePresenter userProfilePresenter = new UserProfilePresenter(userProfileViewModel);
            UserProfileInteractor userProfileInteractor = new UserProfileInteractor(userProfilePresenter, courseDataAccessObject);
            UserProfileController userProfileController = new UserProfileController(userProfileInteractor);

            // Initialize UserProfileState with actual user data
            UserProfileState userProfileState = new UserProfileState();
            userProfileState.setName(user.getName());
            userProfileState.setEmail(user.getEmail());
            userProfileState.setCreationTime(user.getCreationTime().toString());

            // Retrieve all courses from dataAccessObject
            Map<String, Course> courseMap = courseDataAccessObject.getCourseMap();
            List<String> courseCodes = courseMap.values().stream()
                    .map(Course::getCode)
                    .collect(Collectors.toList());

            userProfileState.setCourseCodes(courseCodes);

            // Create and initialize UserprofileView
            UserprofileView userprofileView = new UserprofileView(userProfileController, userProfileState);

            // Create a new JFrame for the UserProfileView
            JFrame userProfileFrame = new JFrame("User Profile");
            userProfileFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            userProfileFrame.setSize(500, 300);
            userProfileFrame.setContentPane(userprofileView);
            userProfileFrame.setVisible(true);

        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading course data", "Error", JOptionPane.ERROR_MESSAGE);

            // Default initialization in case of error
            // Handle error by showing a default view or a placeholder view
            UserProfileViewModel userProfileViewModel = new UserProfileViewModel();
            UserProfilePresenter userProfilePresenter = new UserProfilePresenter(userProfileViewModel);
            UserProfileInteractor userProfileInteractor = new UserProfileInteractor(userProfilePresenter, null); // Pass null or a mock object if needed
            UserProfileController userProfileController = new UserProfileController(userProfileInteractor);
            UserProfileState userProfileState = new UserProfileState();
            userProfileState.setName("Error"); // Placeholder data
            userProfileState.setEmail("error@example.com"); // Placeholder data
            userProfileState.setCreationTime("Error"); // Placeholder data
            userProfileState.setCourseCodes(Arrays.asList()); // Empty list for placeholder

            UserprofileView userprofileView = new UserprofileView(userProfileController, userProfileState);

            // Get the current JFrame and switch its content pane to UserprofileView
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.setContentPane(userprofileView);
            frame.revalidate(); // Refresh the frame to show the new view
        }
    }

    private void handleAddCalendarAction() {
        String selectedEventName = (String) eventComboBox.getSelectedItem();
        if (selectedEventName != null && !selectedEventName.equals("Choose your Event")) {
            CommonCalendarEvent event = getEventDetails(selectedEventName);
            if (event != null) {
                JFrame frame = new JFrame("Event Details");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setSize(400, 600);
                frame.setContentPane(new EventDetailView(event));
                frame.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Selected event details are not valid.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an event.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private CommonCalendarEvent getEventDetails(String eventName) {
        String filePath = "events.csv";
        String line;
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Skip the header line
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(csvSplitBy);
                if (values.length >= 11 && values[0].trim().equals(eventName)) {
                    // Parse the event details from the CSV file
                    String organizer = values[1].trim(); // Organizer
                    LocalDate eventDate = LocalDate.parse(values[3].trim()); // Event Date
                    LocalTime eventTime = LocalTime.parse(values[4].trim()); // Event Time
                    String eventLocation = values[5].trim(); // Event Location

                    // Event End Date and End Time are not used for this view
                    // You can adjust the parsing if needed, but not necessary here
                    LocalDate eventEndDate = LocalDate.parse(values[9].trim()); // Event End Date (not used here)
                    LocalTime eventEndTime = LocalTime.parse(values[10].trim()); // Event End Time (not used here)

                    return new CommonCalendarEvent(
                            organizer,
                            eventName,
                            eventDate,
                            eventEndDate,
                            eventTime,
                            eventEndTime,
                            eventLocation,
                            Integer.parseInt(values[6].trim()), // Max Attendance
                            values[7].trim() // Event Type
                    );
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    private void handleCreateEventAction()  {
        CourseState courseState = courseViewModel.getState();
        courseViewController.executeCreateEvent(courseState.getUsername());
//        updateEventsList();
    }

//    private void handleJoinGroupChatsAction() {
//        // Implement the action for the Join Group Chats button
//        List<String> courseNames = readCoursesFromFile("courses.csv");
//        JDialog courseDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Join Group Chat", true);
//        courseDialog.setLayout(new BorderLayout());
//        courseDialog.setSize(300, 400);
//
//        JList<String> courseList = new JList<>(courseNames.toArray(new String[0]));
//        courseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        courseDialog.add(new JScrollPane(courseList), BorderLayout.CENTER);
//
//        JButton joinButton = new JButton("Join");
//        joinButton.addActionListener(e -> {
//            //selectedCourse is the course name
//            String selectedCourse = courseList.getSelectedValue();
//            String courseCode = null;
//
//
//            String filePath = "courses.csv";
//            String line;
//            String csvSplitBy = ",";
//
//            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
//                // Skip the header
//                br.readLine();
//                while ((line = br.readLine()) != null) {
//                    String[] courseCheck = line.split(csvSplitBy);
//                    if (courseCheck[0].trim().equalsIgnoreCase(selectedCourse)) {
//                        courseCode = courseCheck[1];
//                        break;
//                    }
//
//                }
//            } catch (IOException exception) {
//                exception.printStackTrace();
//            }
//
//
//            if (courseCode != null) {
//                joinGroupChat(courseCode);
//                courseDialog.dispose();
//            } else {
//                JOptionPane.showMessageDialog(courseDialog, "Please select a course to join.");
//            }
//        });
//        courseDialog.add(joinButton, BorderLayout.SOUTH);
//
//        courseDialog.setVisible(true);
//    }
//
//
//    private void joinGroupChat(String courseCode) {
//        // Implement the logic to join the group chat for the selected course
//        int port = GroupChatPort.getPortByCourseCode(courseCode);
//        String host = GroupChatPort.getHostFromFirstLine();
//        //int port = 3001;
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                GroupChatServer server = new GroupChatServer();
//                server.startServer(port, host);
//            }
//        }).start();
//
//        // Give the server a moment to start up
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        // Start the client
//        GroupChatClient groupChatClient = new GroupChatClient();
//
//        groupChatClient.startChat(courseCode, host, port);
//
//
//    }


    private void handleJoinGroupChatsAction() {
        // Implement the action for the Join Group Chats button
        List<String> courseNames = readCoursesFromFile("courses.csv");
        JDialog courseDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Join Group Chat", true);
        courseDialog.setLayout(new BorderLayout());
        courseDialog.setSize(300, 400);

        JList<String> courseList = new JList<>(courseNames.toArray(new String[0]));
        courseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        courseDialog.add(new JScrollPane(courseList), BorderLayout.CENTER);

        JButton joinButton = new JButton("Join");
        joinButton.addActionListener(e -> {
            //selectedCourse is the course name
            String selectedCourse = courseList.getSelectedValue();
            String courseCode = null;

            String filePath = "courses.csv";
            String line;
            String csvSplitBy = ",";

            try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                while ((line = br.readLine()) != null) {
                    String[] courseCheck = line.split(csvSplitBy);
                    if (courseCheck[0].trim().equalsIgnoreCase(selectedCourse)) {
                        courseCode = courseCheck[1];
                        break;
                    }
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }

            if (courseCode != null) {
                joinGroupChat(courseCode);
                courseDialog.dispose();
            } else {
                JOptionPane.showMessageDialog(courseDialog, "Please select a course to join.");
            }
        });
        courseDialog.add(joinButton, BorderLayout.SOUTH);

        courseDialog.setVisible(true);
    }

    private void joinGroupChat(String courseCode) {
        // Implement the logic to join the group chat for the selected course
        int port = GroupChatPort.getPortByCourseCode(courseCode);
        String host = GroupChatPort.getHostFromFirstLine();

        new Thread(new Runnable() {
            @Override
            public void run() {
                GroupChatServer server = new GroupChatServer();
                server.startServer(port, host);
            }
        }).start();

        // Give the server a moment to start up
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Start the client
        GroupChatClient groupChatClient = new GroupChatClient();
        groupChatClient.startChat(courseCode, host, port);
    }


    private void updateUsernameLabel() {
        CourseState state = courseViewModel.getState();
        usernameLabel.setText("Currently logged in: " + state.getUsername());
    }
    public void propertyChange(PropertyChangeEvent evt) {
        if ("eventsUpdated".equals(evt.getPropertyName())) {
            updateEventComboBox();  // Method to update the events combo box
        } else if ("state".equals(evt.getPropertyName())) {
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

    public List<String> readEventsFromFile(String filePath) {
        List<String> events = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Skip the header line
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length > 0) {
                    events.add(values[0].trim());  // Assuming the event name is the first element
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return events;
    }

    private void updateEventComboBox() {
        eventComboBox.removeAllItems();  // Clear existing items
        List<String> eventNames = readEventsFromFile("events.csv");  // Re-read the events from file
        eventComboBox.addItem("Choose your Event");
        for (String eventName : eventNames) {
            eventComboBox.addItem(eventName);
        }
    }
}
