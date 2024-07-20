package usecase.CreateCourse;

public class CreateCourseInputData {
    final private String code;
    final private String name;

    public CreateCourseInputData(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
