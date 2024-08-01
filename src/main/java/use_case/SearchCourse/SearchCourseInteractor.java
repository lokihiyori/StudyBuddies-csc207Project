package use_case.SearchCourse;

import entity.Course;

/**
 * Interactor for searching courses.
 * Implements the SearchCourseInputBoundary interface to handle course search logic.
 */
public class SearchCourseInteractor implements SearchCourseInputBoundary {

    private CourseRepository courseRepository;
    private SearchCourseOutputBoundary outputBoundary;

    /**
     * Constructs a SearchCourseInteractor with the specified course repository and output boundary.
     *
     * @param courseRepository the repository to search courses in
     * @param outputBoundary   the output boundary to present search results
     */
    public SearchCourseInteractor(CourseRepository courseRepository, SearchCourseOutputBoundary outputBoundary) {
        this.courseRepository = courseRepository;
        this.outputBoundary = outputBoundary;
    }

    /**
     * Searches for a course based on the provided input data.
     *
     * @param inputData the input data containing the search query
     */
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
