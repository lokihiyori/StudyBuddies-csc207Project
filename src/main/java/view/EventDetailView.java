package view;

import entity.CommonCalendarEvent;

import javax.swing.*;
import java.awt.*;

public class EventDetailView extends JPanel {

    public EventDetailView(CommonCalendarEvent event) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel eventPanel = new JPanel();
        eventPanel.setLayout(new BoxLayout(eventPanel, BoxLayout.Y_AXIS));
        eventPanel.setBackground(new Color(204, 255, 255));

        JLabel titleLabel = new JLabel("Event Details");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        eventPanel.add(titleLabel);
        eventPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel nameLabel = new JLabel("Event Name: " + event.getName());
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        eventPanel.add(nameLabel);
        eventPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel organizerLabel = new JLabel("Organizer: " + event.getOrganizer());
        organizerLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        eventPanel.add(organizerLabel);
        eventPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel dateLabel = new JLabel("Event Date: " + event.getDate().toString());
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        eventPanel.add(dateLabel);
        eventPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel timeLabel = new JLabel("Event Time: " + event.getTime().toString());
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        eventPanel.add(timeLabel);
        eventPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel endDateLabel = new JLabel("Event End Date: " + event.getEventEndDate().toString());
        endDateLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        eventPanel.add(endDateLabel);
        eventPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel endTimeLabel = new JLabel("Event End Time: " + event.getEventEndTime().toString());
        endTimeLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        eventPanel.add(endTimeLabel);
        eventPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel locationLabel = new JLabel("Event Location: " + event.getLocation());
        locationLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        eventPanel.add(locationLabel);
        eventPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel typeLabel = new JLabel("Event Type: " + event.eventType());
        typeLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        eventPanel.add(typeLabel);
        eventPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        add(eventPanel, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> handleBackAction());
        add(backButton, BorderLayout.SOUTH);
    }

    private void handleBackAction() {
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window != null) {
            window.dispose(); // Close the current window
        }
    }
}
