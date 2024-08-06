package interface_adapter.CreateEvent;

import java.time.LocalDate;
import java.time.LocalTime;

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

    // Because of the previous copy constructor, the default constructor must be explicit.
    public CreateEventState() {}

    public String getDiscription() {
        return discription;
    }

    public String getPlace() {
        return place;
    }

    public String getSporttype() {
        return sporttype;
    }

    public LocalDate getDate() {return date;}
    public String getEventType() {
        return eventType;
    }
    public LocalTime getTime() {
        return time;
    }
    public int getMaxplayers() {
        return maxplayers;
    }
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public void setPlace(String place) {
        this.place = place;
    }
    public void setMaxplayers(int maxplayers) {
        this.maxplayers = maxplayers;
    }

    public void setSporttype(String sporttype) {
        this.sporttype = sporttype;
    }
    public void setDate(LocalDate date) {this.date = date;}
    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getEndDate() {return endDate;}
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public LocalTime getEndTime() {return endTime;}
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }
}

