package view;

import entity.CommonCalendarEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class EventDetailViewTest {

    private EventDetailView view;
    private CommonCalendarEvent event;

    @BeforeEach
    void setUp() {
        // Prepare the event data with the constructor
        event = new CommonCalendarEvent(
                "John Doe",                  // organizer
                "Test Event",                // eventName
                LocalDate.now(),             // eventDate
                LocalDate.now().plusDays(1), // eventEndDate
                LocalTime.of(14, 0),         // eventTime
                LocalTime.of(16, 0),         // eventEndTime
                "Conference Room A",         // eventLocation
                50,                          // maxAttendance
                "Study"                   // eventType
        );

        // Initialize EventDetailView with the event
        view = new EventDetailView(event);
    }

    @Test
    void testEventDetailViewComponents() {
        JPanel eventPanel = (JPanel) view.getComponent(0);
        assertNotNull(eventPanel, "Event panel should not be null");

        // Check labels contain the correct texts
        JLabel nameLabel = (JLabel) eventPanel.getComponent(2);
        assertEquals("Event Name: Test Event", nameLabel.getText());

        JLabel organizerLabel = (JLabel) eventPanel.getComponent(4);
        assertEquals("Organizer: John Doe", organizerLabel.getText());

        // Check the 'Back' button functionality
        JButton backButton = (JButton) view.getComponent(1);
        assertNotNull(backButton, "Back button should not be null");
        assertEquals("Back", backButton.getText());
    }

    @Test
    void testBackButtonAction() {
        // Simulate back button press
        JButton backButton = (JButton) view.getComponent(1);
        backButton.doClick();

        // Ideally, here you would verify if the window has been disposed.
        // This typically requires integration testing with an actual GUI framework handling window events.
    }
}
