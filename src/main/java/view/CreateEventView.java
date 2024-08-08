package view;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.optionalusertools.TimeChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;
import com.github.lgooddatepicker.zinternaltools.TimeChangeEvent;
import interface_adapter.CreateEvent.CreateEventController;
import interface_adapter.CreateEvent.CreateEventState;
import interface_adapter.CreateEvent.CreateEventViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * CreateEventView is a JPanel that provides a user interface for creating events.
 */
public class CreateEventView extends JPanel implements ActionListener, PropertyChangeListener {

    JLabel username;
    Window container;

    public final String viewName = "CreateEventView";
    private final CreateEventViewModel createEventViewModel;
    private final CreateEventController createEventController;
    final JTextField eventPlaceInputField = new JTextField(25);
    final DatePicker eventDateInputField = new DatePicker();
    final TimePicker eventTimeInputField = new TimePicker();
    final DatePicker eventEndDateInputField = new DatePicker();
    final TimePicker eventEndTimeInputField = new TimePicker();
    final JTextField eventNameInputField = new JTextField(25);
    final JTextField eventAttendanceInputField = new JTextField(25);
    final JButton create;
    final JButton cancel;
    final JComboBox<String> eventTypeComboBox;

    /**
     * Constructs a new CreateEventView with the specified view model, controller, and container window.
     *
     * @param createEventViewModel   the CreateEventViewModel instance
     * @param createEventController  the CreateEventController instance
     * @param mainWindows            the container window
     */
    public CreateEventView(CreateEventViewModel createEventViewModel, CreateEventController createEventController, Window mainWindows){
        this.createEventController = createEventController;
        this.createEventViewModel = createEventViewModel;
        this.createEventViewModel.addPropertyChangeListener(this);
        this.container = mainWindows;

        JLabel title = new JLabel("Let's Go for Events");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(new Color(41, 128, 185));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        CreateEventState currentState = createEventViewModel.getState();

        username = new JLabel();
        username.setAlignmentX(Component.CENTER_ALIGNMENT);
        username.setFont(new Font("Arial", Font.BOLD, 20));
        username.setForeground(new Color(200, 200, 200));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel eventNameLabel = new JLabel("Event Name");
        eventNameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        LabelTextPanel eventNameInfo = new LabelTextPanel(eventNameLabel, eventNameInputField);

        JLabel eventPlaceLabel = new JLabel("Event Place");
        eventPlaceLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel eventAttendanceLabel = new JLabel("Max Attendance");
        eventAttendanceLabel.setFont(new Font("Arial", Font.BOLD, 18));

        LabelTextPanel eventPlaceInfo = new LabelTextPanel(eventPlaceLabel, eventPlaceInputField);
        LabelTextPanel eventAttendanceInfo = new LabelTextPanel(eventAttendanceLabel, eventAttendanceInputField);

        eventTypeComboBox = new JComboBox<>();
        eventTypeComboBox.setFont(new Font("Arial", Font.BOLD, 18));
        eventTypeComboBox.addItem("Choose Type of Event");
        eventTypeComboBox.addItem("Group Study");
        eventTypeComboBox.addItem("Group Meeting");
        eventTypeComboBox.addItem("Entertainment");

        JPanel buttons = new JPanel();
        create = new JButton(createEventViewModel.CREATE_EVENT_LABEL);
        create.setBackground(new Color(39, 174, 96));
        create.setForeground(Color.WHITE);
        create.setFont(new Font("Arial", Font.PLAIN, 18));
        buttons.add(create);
        cancel = new JButton(createEventViewModel.CANCEL_BUTTON_LABEL);
        cancel.setBackground(Color.RED);
        cancel.setForeground(Color.WHITE);
        cancel.setFont(new Font("Arial", Font.PLAIN, 18));
        buttons.add(cancel);


        JLabel eventDateLabel = new JLabel("Select Event Start Date");
        eventDateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        eventDateLabel.setFont(new Font("Arial", Font.BOLD, 18));
        eventDateLabel.setForeground(Color.BLACK);

        JLabel eventEndDateLabel = new JLabel("Select Event End Date");
        eventEndDateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        eventEndDateLabel.setFont(new Font("Arial", Font.BOLD, 18));
        eventEndDateLabel.setForeground(Color.BLACK);

        JLabel eventTimeLabel = new JLabel("Select Event Start Time");
        eventTimeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        eventTimeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        eventTimeLabel.setForeground(Color.BLACK);

        JLabel eventEndTimeLabel = new JLabel("Select Event End Time");
        eventEndTimeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        eventEndTimeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        eventEndTimeLabel.setForeground(Color.BLACK);

        create.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(create)) {
                            CreateEventState currentState = createEventViewModel.getState();

                            createEventController.executeMakeEvent(
                                    currentState.getUsername(),
                                    currentState.getDiscription(),
                                    currentState.getPlace(),
                                    currentState.getDate(),
                                    currentState.getEndDate(),
                                    currentState.getTime(),
                                    currentState.getEndTime(),
                                    currentState.getSporttype(),
                                    currentState.getEventType(),
                                    currentState.getMaxplayers()
                            );
                            createEventViewModel.notifyEventAdded();
                        }
                    }
                }
        );

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getSource().equals(cancel)){
                    CreateEventState currentState = createEventViewModel.getState();
                    createEventController.executeCancel(currentState.getUsername());
                }
            }
        });
        eventDateInputField.addDateChangeListener(
                new DateChangeListener() {
                    @Override
                    public void dateChanged(DateChangeEvent dateChangeEvent) {
                        CreateEventState currentState = createEventViewModel.getState();
                        LocalDate date = eventDateInputField.getDate();
                        currentState.setDate(date);
                        createEventViewModel.setState(currentState);
                    }
                });
        eventEndDateInputField.addDateChangeListener(
                new DateChangeListener() {
                    @Override
                    public void dateChanged(DateChangeEvent dateChangeEvent) {
                        CreateEventState currentState = createEventViewModel.getState();
                        LocalDate date = eventEndDateInputField.getDate();
                        currentState.setEndDate(date);
                        createEventViewModel.setState(currentState);
                    }
                });
        eventTimeInputField.addTimeChangeListener(
                new TimeChangeListener() {
                    @Override
                    public void timeChanged(TimeChangeEvent timeChangeEvent) {
                        CreateEventState currentState = createEventViewModel.getState();
                        LocalTime time = eventTimeInputField.getTime();
                        currentState.setTime(time);
                        createEventViewModel.setState(currentState);
                    }
                });
        eventEndTimeInputField.addTimeChangeListener(
                new TimeChangeListener() {
                    @Override
                    public void timeChanged(TimeChangeEvent timeChangeEvent) {
                        CreateEventState currentState = createEventViewModel.getState();
                        LocalTime time = eventEndTimeInputField.getTime();
                        currentState.setEndTime(time);
                        createEventViewModel.setState(currentState);
                    }
                });
        eventNameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        CreateEventState currentState = createEventViewModel.getState();
                        String text = eventNameInputField.getText() + e.getKeyChar();
                        currentState.setDiscription(text);
                        createEventViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });
        eventPlaceInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        CreateEventState currentState = createEventViewModel.getState();
                        String text = eventPlaceInputField.getText() + e.getKeyChar();
                        currentState.setPlace(text);
                        createEventViewModel.setState(currentState);
                    }
                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });


        eventTypeComboBox.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        CreateEventState currentState = createEventViewModel.getState();
                        String lvl = (String) eventTypeComboBox.getSelectedItem();
//                        currentState.setLvl(lvl);
                        createEventViewModel.setState(currentState);
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(username);
        this.add(eventNameInfo);
        this.add(eventPlaceInfo);
        this.add(eventAttendanceInfo);
        this.add(eventTypeComboBox);
        this.add(eventDateLabel);
        this.add(eventDateInputField);
        this.add(eventEndDateLabel);
        this.add(eventEndDateInputField);
        this.add(eventTimeLabel);
        this.add(eventTimeInputField);
        this.add(eventEndTimeLabel);
        this.add(eventEndTimeInputField);
        this.add(buttons);


    }

    /**
     * Handles the action event.
     *
     * @param evt the ActionEvent object
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    /**
     * Handles property changes in the view model.
     *
     * @param evt the PropertyChangeEvent object
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Handle property changes
        CreateEventState state = (CreateEventState) evt.getNewValue();
        username.setText(state.getUsername());

    }

    /**
     * Gets the CreateEventController associated with this view.
     *
     * @return the CreateEventController instance
     */
    public CreateEventController getController() {
        return createEventController;
    }
}




