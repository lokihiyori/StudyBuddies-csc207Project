package interface_adapter.GoToCourse;

public class CourseState {
    private String username = "";

    public CourseState(CourseState copy) {
        username = copy.username;
    }

    // Because of the previous copy constructor, the default constructor must be explicit.
    public CourseState() {}

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
