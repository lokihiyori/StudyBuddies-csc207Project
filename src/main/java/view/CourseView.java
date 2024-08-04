package view;

import entity.Course;
import interface_adapter.GoToCourse.CourseState;
import interface_adapter.GoToCourse.CourseViewController;
import interface_adapter.GoToCourse.CourseViewModel;
import interface_adapter.SignUp.SignUpState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CourseView extends JPanel implements PropertyChangeListener {

    private final CourseViewModel courseViewModel;
    private final CourseViewController courseViewController;
    public final String viewName = "CourseView";

    private JLabel usernameLabel;
    private JButton logOutButton;

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
        usernameLabel = new JLabel("Currently logged in: ");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        centerPanel.add(usernameLabel);
        add(centerPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false);  // Make the button panel transparent
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


    private void updateUsernameLabel() {
        CourseState state = courseViewModel.getState();
        usernameLabel.setText("Currently logged in: " + state.getUsername());
    }
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            updateUsernameLabel();
        }
    }
   }

