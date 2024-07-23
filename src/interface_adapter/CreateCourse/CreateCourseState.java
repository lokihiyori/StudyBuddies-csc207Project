package interface_adapter.CreateCourse;

public class CreateCourseState {
    private String code;
    private String name;

    public CreateCourseState(String name, String code) {
        this.name = name;
        this.code = code;
    }
    public CreateCourseState(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
