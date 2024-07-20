package use_case.SearchCourse;

import entity.Course;

public class SearchCourseInteractor implements SearchCourseInputBoundary {

    private CourseRepository courseRepository;
    private SearchCourseOutputBoundary outputBoundary;

    public SearchCourseInteractor(CourseRepository courseRepository, SearchCourseOutputBoundary outputBoundary) {
        this.courseRepository = courseRepository;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void searchCourse(SearchCourseInputData inputData) {
        String query = inputData.getQuery();
        Course courseByCode = courseRepository.findCourseByCode(query);
        Course courseByName = courseRepository.findCourseByName(query);

        if (courseByCode != null) {
            outputBoundary.present(new SearchCourseOutputData(courseByCode, "Course found by code: " + courseByCode));
        } else if (courseByName != null) {
            outputBoundary.present(new SearchCourseOutputData(courseByName, "Course found by Name: " + courseByName));
        } else {
            outputBoundary.present(new SearchCourseOutputData(null, "Course not found."));
        }
    }


}
