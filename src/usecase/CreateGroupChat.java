package usecase;

import data_access.CourseListDAO;
import entity.Course;
import entity.GroupChat;

import java.util.Scanner;
public class CreateGroupChat {

    public static void main(String[] args) {
        CourseListDAO repository = new CourseListDAO();
        repository.addCourse(new Course("Software Design", "CSC207", "CSC207_Chat"));
        repository.addCourse(new Course("Calculus I", "MAT141", "MAT141_Chat"));
        repository.addCourse(new Course("Physics I", "PHY131", "PHY131_Chat"));

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the course ID or course name to search: ");
        String userInput = scanner.nextLine();

        Course courseByName = repository.findCourseByName(userInput);
        Course courseByCode = repository.findCourseByCode(userInput.toUpperCase());

        String create = "No";

        if (courseByCode != null) {
            System.out.println("Course found by Code: " + courseByCode);
            // go to group chat or not
        } else if (courseByName != null) {
            System.out.println("Course found by Name: " + courseByName);
            // go to group chat or not
        } else {
            System.out.println("Course not found. Do you want to create this course with its group chat?");
            create = scanner.nextLine().toUpperCase();
        }

        if (create.equals("YES")) {
            System.out.println("Please enter the name of the course: ");
            String courseName = scanner.nextLine();
            System.out.println("Please enter the code of the course: ");
            String courseCode = scanner.nextLine().toUpperCase();

            //create group chat
            GroupChat gc = new GroupChat(courseCode);

            //add person into the group chat

            //create new course object and append it to the course list
            Course newCourse = new Course(courseName, courseCode, courseCode); //should replace by GroupChat
            System.out.println("The course: " + newCourse +" has been created.");
        }
        else{
            // go back to personal page (go to your groupchat/ go to search bar)
        }

    }

}
