package app;

import data_access.CourseDataAccessObject;
import data_access.FileUserDataAccessObject;
import entity.User;
import interface_adapter.UserProfile.UserProfileController;
import interface_adapter.UserProfile.UserProfilePresenter;
import interface_adapter.UserProfile.UserProfileState;
import interface_adapter.UserProfile.UserProfileViewModel;
import use_case.UserProfile.UserProfileInteractor;
import use_case.UserProfile.UserProfileOutputBoundary;
import use_case.UserProfile.UserProfileOutputData;
import view.UserprofileView;
import entity.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserProfileUseCaseFactory {
    private UserProfileUseCaseFactory() {}
    public static UserprofileView create(
            FileUserDataAccessObject userDataAccessObject,
            CourseDataAccessObject courseDataAccessObject,
            String username
    ) throws IOException {
        User user = userDataAccessObject.get(username);
        if (user == null) {
            throw new RuntimeException("User not found. ");
        }

        UserProfileViewModel userProfileViewModel = new UserProfileViewModel();
        UserProfileOutputBoundary presenter = new UserProfilePresenter(userProfileViewModel);
        UserProfileInteractor userProfileInteractor = new UserProfileInteractor(presenter,courseDataAccessObject );
        UserProfileController userProfileController = new UserProfileController(userProfileInteractor);

        // Retrieve all courses from dataAccessObject
        Map<String, Course> courseMap = courseDataAccessObject.getCourseMap();
        List<String> courseCodes = courseMap.values().stream()
                .map(Course::getCode)
                .collect(Collectors.toList());

        // Initialize UserProfileState and UserProfileViewModel
        UserProfileState state = new UserProfileState();
        state.setName(user.getName());
        state.setEmail(user.getEmail());
        state.setCreationTime(LocalDateTime.now().toString());
        state.setCourseCodes(courseCodes);

        // Create and initialize UserprofileView
        return new UserprofileView(userProfileController, state);

    }
}
