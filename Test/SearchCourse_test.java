//import data_access.CourseListDAO;
import data_access.CourseDataAccessObject;
import entity.Course;
import entity.GroupChat;
import interface_adapter.SearchCourse.SearchCourseController;
import interface_adapter.SearchCourse.SearchCoursePresenter;
import interface_adapter.SearchCourse.SearchCourseViewModel;
import org.junit.Before;
import org.junit.Test;
import use_case.SearchCourse.SearchCourseInputBoundary;
import use_case.SearchCourse.SearchCourseInteractor;

import static org.junit.Assert.*;

public class SearchCourse_test {
    private CourseDataAccessObject courseListDAO;
    private SearchCourseViewModel viewModel;
    private SearchCoursePresenter presenter;
    private SearchCourseInputBoundary searchCourseInteractor;
    private SearchCourseController controller;

    @Before
    public void setUp() {
        courseListDAO = new CourseDataAccessObject();
        viewModel = new SearchCourseViewModel();
        presenter = new SearchCoursePresenter(viewModel);
        searchCourseInteractor = new SearchCourseInteractor(courseListDAO, presenter);
        controller = new SearchCourseController(searchCourseInteractor);

        // Adding some sample courses to the repository
        GroupChat gc1 = new GroupChat("CSC207");
        GroupChat gc2 = new GroupChat("MAT141");
        GroupChat gc3 = new GroupChat("PHY131");

        // Adding some sample courses to the repository
        courseListDAO.addCourse(new Course("SOFTWARE DESIGN", "CSC207", gc1));
        courseListDAO.addCourse(new Course("CALCULUS I", "MAT141", gc2));
        courseListDAO.addCourse(new Course("PHYSICS I", "PHY131", gc3));
    }

    @Test
    public void testSearchCourseByCode_Found() {
        controller.handleInput("CSC207");
        assertNotNull(viewModel.getCourse());
        assertEquals("CSC207", viewModel.getCourse().getCode());
        assertEquals("SOFTWARE DESIGN", viewModel.getCourse().getName());
        assertEquals("Course found by code: Course name: SOFTWARE DESIGN, Course code: CSC207", viewModel.getMessage());
    }

    @Test
    public void testSearchCourseByName_Found() {
        controller.handleInput("SOFTWARE DESIGN");
        assertNotNull(viewModel.getCourse());
        assertEquals("CSC207", viewModel.getCourse().getCode());
        assertEquals("SOFTWARE DESIGN", viewModel.getCourse().getName());
        assertEquals("Course found by Name: Course name: SOFTWARE DESIGN, Course code: CSC207", viewModel.getMessage());
    }

    @Test
    public void testSearchCourse_NotFound() {
        controller.handleInput("BIO101");
        assertNull(viewModel.getCourse());
        assertEquals("Course not found.", viewModel.getMessage());
    }


}
