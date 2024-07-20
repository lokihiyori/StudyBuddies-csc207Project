package app;

import entity.Course;
import entity.GroupChat;
import data_access.CourseListDAO;
import interface_adapter.SearchCourse.SearchCourseController;
import interface_adapter.SearchCourse.SearchCoursePresenter;
import interface_adapter.SearchCourse.SearchCourseViewModel;
import use_case.SearchCourse.SearchCourseInputBoundary;
import use_case.SearchCourse.SearchCourseOutputBoundary;
import use_case.SearchCourse.SearchCourseInteractor;
import view.SearchCourseUI;

import java.util.Scanner;

public class SearchCourseMain {
    public static void main(String[] args) {
        CourseListDAO courseList = new CourseListDAO();
        SearchCourseViewModel viewModel = new SearchCourseViewModel();
        SearchCourseOutputBoundary presenter = new SearchCoursePresenter(viewModel);
        SearchCourseInputBoundary searchCourseInteractor = new SearchCourseInteractor(courseList, presenter);
        SearchCourseController controller = new SearchCourseController(searchCourseInteractor);

        GroupChat gc1 = new GroupChat("CSC207");
        GroupChat gc2 = new GroupChat("MAT141");
        GroupChat gc3 = new GroupChat("PHY131");

        // Adding some sample courses to the repository
        courseList.addCourse(new Course("Software Design", "CSC207", gc1));
        courseList.addCourse(new Course("Calculus I", "MAT141", gc2));
        courseList.addCourse(new Course("Physics I", "PHY131", gc3));


        SearchCourseUI searchCourseUI = new SearchCourseUI(controller, viewModel);
        searchCourseUI.display();

        /*
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the course code or course name to search: ");
        String userInput = scanner.nextLine();

        controller.handleInput(userInput);

        System.out.println(viewModel.getMessage());

        if (viewModel.getCourse() != null) {
            //course exists, you can join the groupchat
            //System.out.println(viewModel.getCourse());
        }
        else{
            //course doesn't exist, create the course or go back to the personal page
        }

        scanner.close();

         */
    }

}
