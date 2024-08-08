package app;
import data_access.FileUserDataAccessObject;
import interface_adapter.CreateEvent.CreateEventViewModel;
import interface_adapter.GoToCourse.CoursePresenter;
import interface_adapter.GoToCourse.CourseViewModel;
import interface_adapter.GroupChatViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.logged_In.LoggedInViewModel;
import interface_adapter.logged_In.LoggedIncontroller;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import use_case.GoToCourse.GoToCourseInputBoundary;
import use_case.GoToCourse.GoToCourseInteractor;
import use_case.GoToCourse.GoToCourseOutputBoundary;
import view.LoggedInView;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class LoggedInUseCaseFactoryTest {

    private ViewManagerModel viewManagerModel;
    private LoggedInViewModel loggedInViewModel;
    private CourseViewModel courseViewModel;
    private CreateEventViewModel createEventViewModel;
    private FileUserDataAccessObject userDataAccessObject;

    @BeforeEach
    void setUp() {
        viewManagerModel = mock(ViewManagerModel.class);
        loggedInViewModel = mock(LoggedInViewModel.class);
        courseViewModel = mock(CourseViewModel.class);
        createEventViewModel = mock(CreateEventViewModel.class);
        userDataAccessObject = mock(FileUserDataAccessObject.class);
    }

    @Test
    void testCreateLoggedInView() {
        LoggedInView loggedInView = LoggedInUseCaseFactory.create(
                viewManagerModel,
                loggedInViewModel,
                courseViewModel,
                createEventViewModel,
                userDataAccessObject
        );

        assertNotNull(loggedInView);
        assertNotNull(loggedInView.getController());
        assertTrue(loggedInView.getController() instanceof LoggedIncontroller);

        LoggedIncontroller controller = loggedInView.getController();
        assertNotNull(controller.getGoToCourseInputBoundary());

        assertTrue(controller.getGoToCourseInputBoundary() instanceof GoToCourseInteractor);

        GoToCourseInteractor goToCourseInteractor = (GoToCourseInteractor) controller.getGoToCourseInputBoundary();
        assertNotNull(goToCourseInteractor.getUserDataAccessObject());
        assertNotNull(goToCourseInteractor.getPresenter());
    }
}
