package view;
import data_access.CourseDataAccessObject;
import data_access.FileUserDataAccessObject;
import SocketIO.GroupChatPort;
import SocketIO.GroupChatServer;
import interface_adapter.GroupChat.GroupChatFacade;
import interface_adapter.GroupChat.GroupChatFacadeImpl;

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

/**
 * CourseView is a JPanel that provides a user interface for managing courses, events, and user profiles.
 */
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

    /**
     * Constructs a new CourseView with the specified view models and controller.
     *
     * @param courseViewModel       the CourseViewModel instance
     * @param courseViewController  the CourseViewController instance
     * @param createEventViewModel  the CreateEventViewModel instance
     */
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

    /**
     * Handles the log out action.
     */
    private void handleLogOut() {
        courseViewController.executeLogOut();
    }

    /**
     * Handles the action for displaying the user's profile.
     */
    private void handleProfileAction() {
        try {
            // Initialize FileUserDataAccessObject
            String userCsvPath = "user.csv"; // Ensure this path is correct
            FileUserDataAccessObject userDataAccessObject = new FileUserDataAccessObject(userCsvPath, new CommonUserFactory());


            // Retrieve user data
            String username = courseViewModel.getState().getUsername();
            User user = userDataAccessObject.get(username);
            if (user == null) {
                throw new RuntimeException("User not found.");
            }

                // Initialize CourseDataAccessObject
                String courseCsvPath = "courses.csv";
                CourseFactory courseFactory = new CourseFactory();
                GroupChatFactory groupChatFactory = new GroupChatFactory();
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
                JFrame userProfileFrame = new JFrame("User Profile: " + user.getName());
                userProfileFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                userProfileFrame.setSize(500, 300);
                userProfileFrame.setContentPane(userprofileView);
                userProfileFrame.setVisible(true);
            } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Handles the action for adding an event to the calendar.
     */
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

    /**
     * Retrieves the details of an event from the events CSV file.
     *
     * @param eventName the name of the event
     * @return a CommonCalendarEvent object containing the event details
     */
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

    /**
     * Handles the action for creating a new event.
     */
    private void handleCreateEventAction()  {
        CourseState courseState = courseViewModel.getState();
        courseViewController.executeCreateEvent(courseState.getUsername());
    }

    /**
     * Handles the action for joining group chats.
     */
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

    /**
     * Joins the group chat for the specified course code.
     *
     * @param courseCode the code of the course
     */
    private void joinGroupChat(String courseCode) {
        // Implement the logic to join the group chat for the selected course

        //start server
        GroupChatServer server = new GroupChatServer();
        int port = GroupChatPort.getPortByCourseCode(courseCode);
        String host = GroupChatPort.getHostFromFirstLine();
        server.startServer(port, host);

        //Use the facade to start the client and join the group chat
        GroupChatFacade groupChatFacade = new GroupChatFacadeImpl();
        groupChatFacade.startGroupChat(courseCode, host, port);

    }

    /**
     * Updates the username label with the currently logged-in user's name.
     */
    private void updateUsernameLabel() {
        CourseState state = courseViewModel.getState();
        usernameLabel.setText("Currently logged in: " + state.getUsername());
    }

    /**
     * Handles property changes in the view models.
     *
     * @param evt the PropertyChangeEvent object
     */
    public void propertyChange(PropertyChangeEvent evt) {
        if ("eventsUpdated".equals(evt.getPropertyName())) {
            updateEventComboBox();  // Method to update the events combo box
        } else if ("state".equals(evt.getPropertyName())) {
            updateUsernameLabel();
        }
    }

    /**
     * Reads the list of courses from the specified CSV file.
     *
     * @param filePath the path to the CSV file
     * @return a list of course names
     */
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

    /**
     * Reads the list of events from the specified CSV file.
     *
     * @param filePath the path to the CSV file
     * @return a list of event names
     */
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

    /**
     * Updates the events combo box with the latest events from the CSV file.
     */
    private void updateEventComboBox() {
        eventComboBox.removeAllItems();  // Clear existing items
        List<String> eventNames = readEventsFromFile("events.csv");  // Re-read the events from file
        eventComboBox.addItem("Choose your Event");
        for (String eventName : eventNames) {
            eventComboBox.addItem(eventName);
        }
    }

    /**
     * Gets the CourseViewController associated with this view.
     *
     * @return the CourseViewController instance
     */
    public CourseViewController getController() {
        return courseViewController;
    }
}
