package data_access;

import entity.CalendarEvent;
import entity.CalendarEventFactory;
import entity.UserFactory;
import use_case.MakeEvent.makeEventDataAccessInterface;
import use_case.joinEvent.joinEventDataAccessInterface;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FileEventDataAccessObject implements makeEventDataAccessInterface, joinEventDataAccessInterface {
    private final File csvFileEvent;

    private final Map<String, CalendarEvent> events = new HashMap<>();
    private UserFactory userFactory;
    private CalendarEventFactory calendarEventFactory;

    public FileEventDataAccessObject(String csvPathEvent, UserFactory userFactory, CalendarEventFactory calendarEventFactory) throws IOException {
        this.userFactory = userFactory;
        this.calendarEventFactory = calendarEventFactory;
        csvFileEvent = new File(csvPathEvent);

        if (csvFileEvent.length() == 0) {
            save();
        } else {
            try (BufferedReader reader = Files.newBufferedReader(Paths.get(csvFileEvent.toURI()))) {
                String line;
                reader.readLine(); // Skip header
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data.length > 10) { // Ensuring there's enough data
                        CalendarEvent calendarEvent = parseEventCsvLine(data);
                        events.put(calendarEvent.getName(), calendarEvent);
                    }
                }
            }
        }
    }

    private CalendarEvent parseEventCsvLine(String[] data) {
        String eventName = data[0];
        String organizer = data[1];
        String description = data[2];
        LocalDate date = LocalDate.parse(data[3], DateTimeFormatter.ISO_LOCAL_DATE);
        LocalTime time = LocalTime.parse(data[4], DateTimeFormatter.ISO_LOCAL_TIME);
        String location = data[5];
        int maxAttendance = Integer.parseInt(data[6]);
        String eventType = data[7];
        List<String> attendance = List.of(data[8].split(";"));
        LocalDate endDate = LocalDate.parse(data[9], DateTimeFormatter.ISO_LOCAL_DATE);
        LocalTime endTime = LocalTime.parse(data[10], DateTimeFormatter.ISO_LOCAL_TIME);

        CalendarEvent event = calendarEventFactory.create(eventName, date, endDate, time, endTime, organizer, maxAttendance, eventType, location);
        event.setEventDescription(description);
        attendance.forEach(event::addAttendance);
        return event;
    }

    public CalendarEvent getCalendarEvent(String eventName) {
        return events.get(eventName);
    }

    public boolean existsByName(String name) {
        return events.containsKey(name);
    }

    public void save(CalendarEvent event) {
        events.put(event.getName(), event);
        save();
    }

    public void addParticipant(String eventName, String username) {
        if (existsByName(eventName)) {
            CalendarEvent calendarEvent = events.get(eventName);
            calendarEvent.getAttendance().add(username);
            save();
        }
    }

    public void save() {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(csvFileEvent.toURI()))) {
            writer.write("Event Name,Organizer,Event Description,Event Date,Event Time,Event Location,Max Attendance,Event Type,Attendance,Event End Date,Event End Time\n");
            for (CalendarEvent event : events.values()) {
                List<String> attendance = event.getAttendance();
                String attendanceString = String.join(";", attendance);  // Using semicolon to separate attendees
                writer.write(String.format("%s,%s,%s,%s,%s,%s,%d,%s,%s,%s,%s\n",
                        event.getName(), event.getOrganizer(), event.getEventDescription(),
                        event.getDate().format(DateTimeFormatter.ISO_LOCAL_DATE),
                        event.getTime().format(DateTimeFormatter.ISO_LOCAL_TIME),
                        event.getLocation(), event.getMaxAttendance(),
                        event.eventType(), attendanceString,
                        event.getEventEndDate().format(DateTimeFormatter.ISO_LOCAL_DATE),
                        event.getEventEndTime().format(DateTimeFormatter.ISO_LOCAL_TIME)));
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to save events to CSV", e);
        }
    }
}
