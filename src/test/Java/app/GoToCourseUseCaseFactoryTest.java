package app;
import data_access.FileEventDataAccessObject;
import data_access.FileUserDataAccessObject;
import interface_adapter.CreateEvent.CreateEventViewModel;
import interface_adapter.GoToCourse.CoursePresenter;
import interface_adapter.GoToCourse.CourseViewController;
import interface_adapter.GoToCourse.CourseViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_In.LoggedInViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_case.CreateEvent.CreateEventInputBoundary;
import use_case.CreateEvent.CreateEventInteractor;
import use_case.CreateEvent.CreateEventOutputBoundary;
import use_case.GoToCourse.GoToCourseInputBoundary;
import use_case.GoToCourse.GoToCourseInteractor;
import use_case.joinEvent.joinEventInputBoundary;
import use_case.joinEvent.joinEventInteractor;
import use_case.joinEvent.joinEventOutputBoundary;
import view.CourseView;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class GoToCourseUseCaseFactoryTest {

    private ViewManagerModel viewManagerModel;
    private CourseViewModel courseViewModel;
    private LoggedInViewModel loggedInViewModel;
    private CreateEventViewModel createEventViewModel;
    private FileUserDataAccessObject userDataAccessObject;
    private FileEventDataAccessObject fileEventDataAccessObject;

    @BeforeEach
    void setUp() {
        viewManagerModel = mock(ViewManagerModel.class);
        courseViewModel = mock(CourseViewModel.class);
        loggedInViewModel = mock(LoggedInViewModel.class);
        createEventViewModel = mock(CreateEventViewModel.class);
        userDataAccessObject = mock(FileUserDataAccessObject.class);
        fileEventDataAccessObject = mock(FileEventDataAccessObject.class);
    }

    @Test
    void testCreateCourseViewCreation() {
        CourseView courseView = GoToCourseUseCaseFactory.create(
                viewManagerModel,
                courseViewModel,
                loggedInViewModel,
                createEventViewModel,
                userDataAccessObject,
                fileEventDataAccessObject
        );

        assertNotNull(courseView);
        assertNotNull(courseView.getController());
        assertTrue(courseView.getController() instanceof CourseViewController);

        CourseViewController controller = courseView.getController();
        assertNotNull(controller.getGoToCourseInputBoundary());
        assertNotNull(controller.getJoinEventUseCaseInteractor());
        assertNotNull(controller.getCreateEventInteractor());

        assertTrue(controller.getGoToCourseInputBoundary() instanceof GoToCourseInteractor);
        assertTrue(controller.getJoinEventUseCaseInteractor() instanceof joinEventInteractor);
        assertTrue(controller.getCreateEventInteractor() instanceof CreateEventInteractor);

        GoToCourseInteractor goToCourseInteractor = (GoToCourseInteractor) controller.getGoToCourseInputBoundary();
        assertNotNull(goToCourseInteractor.getUserDataAccessObject());
        assertNotNull(goToCourseInteractor.getPresenter());

        joinEventInteractor joinEventInteractor = (joinEventInteractor) controller.getJoinEventUseCaseInteractor();
        assertNotNull(joinEventInteractor.getFileEventDataAccessObject());
        assertNotNull(joinEventInteractor.getPresenter());

        CreateEventInteractor createEventInteractor = (CreateEventInteractor) controller.getCreateEventInteractor();
        assertNotNull(createEventInteractor.getUserDataAccessObject());
        assertNotNull(createEventInteractor.getOutputBoundary());
    }
}
