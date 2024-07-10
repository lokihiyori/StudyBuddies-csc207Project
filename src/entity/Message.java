package entity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Message {
    private String content;
    private LocalDateTime timestamp;
    private Users sender;
    private GroupChat receiver;
    private String courseId;
    private List<Users> members;

    public Message(String content, Users sender,
                   GroupChat receiver, LocalDateTime timestamp,
                   String courseId) {
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
        this.timestamp = timestamp;
        this.courseId = courseId;
        this.members = new ArrayList<>();
    }

    public String getContent() {
        return content;
    }
    public LocalDateTime getdate() {
        return timestamp;
    }
    public Users getSender() {
        return sender;
    }

    public GroupChat getReceiver() {
        return receiver;
    }

    public String getCourseId() {
        return courseId;
    }

    public List<Users> getMembers() {
        return members;
    }

    @Override
    public String toString() {
        return "GroupChat{" +
                "courseId='" + courseId + '\'' +
                ", members=" + members +
                '}';
    }
}
