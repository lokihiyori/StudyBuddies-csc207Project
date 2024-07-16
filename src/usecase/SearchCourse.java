package usecase;

import data_access.CourseListDAO;
import entity.Course;

import java.util.Scanner;

public class SearchCourse {
    public static void main(String[] args) {
        CourseListDAO repository = new CourseListDAO();

        // Adding some sample courses to the repository
        repository.addCourse(new Course("Software Design", "CSC207", "CSC207_Chat"));
        repository.addCourse(new Course("Calculus I", "MAT141", "MAT141_Chat"));
        repository.addCourse(new Course("Physics I", "PHY131", "PHY131_Chat"));
        //Course test = new Course("Software Design", "CSC207", "CSC207_Chat");
        //System.out.println(test);
        boolean finish = false;

        while (finish==false){
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the course ID or course name to search: ");
            String userInput = scanner.nextLine();

            Course courseByCode = repository.findCourseByCode(userInput);
            Course courseByName = repository.findCourseByName(userInput);

            if (courseByCode != null) {
                System.out.println("Course found by Code: " + courseByCode);
            } else if (courseByName != null) {
                System.out.println("Course found by Name: " + courseByName);
            } else {
                System.out.println("Course not found.");
            }

            //Scanner sc = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.equals("exit")){
                finish = true;
                scanner.close();
            }
        }
/*
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the course ID or course name to search: ");
        String userInput = scanner.nextLine();

        Course courseByCode = repository.findCourseByCode(userInput);
        Course courseByName = repository.findCourseByName(userInput);

        if (courseByCode != null) {
            System.out.println("Course found by Code: " + courseByCode);
        } else if (courseByName != null) {
            System.out.println("Course found by Name: " + courseByName);
        } else {
            System.out.println("Course not found.");
        }
 */
        //scanner.close();
    }
}
