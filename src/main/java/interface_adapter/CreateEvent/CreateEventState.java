package interface_adapter.CreateEvent;

import java.time.LocalDate;
import java.time.LocalTime;


/**
 * Represents the state of an event being created or managed.
 * Contains details such as event date, time, location, and other attributes.
 */
public class CreateEventState {
    private String username = "";
    private String place = "";
    private LocalDate date = LocalDate.now();
    private LocalDate endDate = LocalDate.now();
    private LocalTime time = LocalTime.now();
    private LocalTime endTime = LocalTime.now();
    private String eventType = "";
    private int maxplayers=0;
    private String sporttype = "";
    private String discription="";

    /**
     * Copy constructor that creates a new instance of CreateEventState by copying the attributes
     * from the provided CreateEventState instance.
     *
     * @param copy the CreateEventState instance to copy attributes from
     */
    public CreateEventState(CreateEventState copy) {

        username = copy.username;
        place = copy.place;
        date = copy.date;
        endDate = copy.endDate;
        time = copy.time;
        endTime = copy.endTime;
        eventType = copy.eventType;
        maxplayers = copy.maxplayers;
        sporttype = copy.sporttype;
        discription = copy.discription;
    }

    /**
     * Default constructor that initializes a new instance of CreateEventState with default values.
     */
    // Because of the previous copy constructor, the default constructor must be explicit.
    public CreateEventState() {}

    /**
     * Returns the description of the event.
     *
     * @return the event description
     */
    public String getDiscription() {
        return discription;
    }

    /**
     * Returns the location of the event.
     *
     * @return the event location
     */
    public String getPlace() {
        return place;
    }

    /**
     * Returns the type of sport associated with the event.
     *
     * @return the sport type
     */
    public String getSporttype() {
        return sporttype;
    }

    /**
     * Returns the start date of the event.
     *
     * @return the event start date
     */
    public LocalDate getDate() {return date;}

    /**
     * Returns the type of the event.
     *
     * @return the event type
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * Returns the start time of the event.
     *
     * @return the event start time
     */
    public LocalTime getTime() {
        return time;
    }
    /**
     * Returns the maximum number of players for the event.
     *
     * @return the maximum number of players
     */
    public int getMaxplayers() {
        return maxplayers;
    }

    /**
     * Sets the type of the event.
     *
     * @param eventType the new event type
     */
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    /**
     * Sets the description of the event.
     *
     * @param discription the new event description
     */
    public void setDiscription(String discription) {
        this.discription = discription;
    }

    /**
     * Sets the location of the event.
     *
     * @param place the new event location
     */
    public void setPlace(String place) {
        this.place = place;
    }

    /**
     * Sets the maximum number of players for the event.
     *
     * @param maxplayers the new maximum number of players
     */
    public void setMaxplayers(int maxplayers) {
        this.maxplayers = maxplayers;
    }

    /**
     * Sets the type of sport associated with the event.
     *
     * @param sporttype the new sport type
     */
    public void setSporttype(String sporttype) {
        this.sporttype = sporttype;
    }

    /**
     * Sets the start date of the event.
     *
     * @param date the new event start date
     */
    public void setDate(LocalDate date) {this.date = date;}

    /**
     * Sets the start time of the event.
     *
     * @param time the new event start time
     */
    public void setTime(LocalTime time) {
        this.time = time;
    }

    /**
     * Returns the username of the person creating or managing the event.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }
    /**
     * Sets the username of the person creating or managing the event.
     *
     * @param username the new username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the end date of the event.
     *
     * @return the event end date
     */
    public LocalDate getEndDate() {return endDate;}

    /**
     * Sets the end date of the event.
     *
     * @param endDate the new event end date
     */
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    /**
     * Returns the end time of the event.
     *
     * @return the event end time
     */

    public LocalTime getEndTime() {return endTime;}

    /**
     * Sets the end time of the event.
     *
     * @param endTime the event end time.
     */
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }
}

