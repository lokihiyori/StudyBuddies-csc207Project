package view;

import data_access.FileUserDataAccessObject;
import entity.UserFactory;
import interface_adapter.UserProfile.UserProfileController;
import interface_adapter.UserProfile.UserProfilePresenter;
import interface_adapter.UserProfile.UserProfileState;
import interface_adapter.UserProfile.UserProfileViewModel;
import use_case.UserProfile.UserProfileInputData;
import use_case.UserProfile.UserProfileInteractor;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class UserprofileView {
    public static void main(String[] args) {
        // Set up the clean architecture layers
        UserProfilePresenter presenter = new UserProfilePresenter();
        UserProfileInteractor interactor = new UserProfileInteractor(presenter);
        UserProfileController controller = new UserProfileController(interactor);

        // Simulate a request to create a user profile
        List<String> courses = Arrays.asList("CSC207", "CSC236", "MAT237");
        controller.createUserProfile("John", "password123","john@gmail.com" ,LocalDateTime.now());

        // Retrieve the output from the presenter
        UserProfileState state = new UserProfileState();
        state.setUserProfileViewModel(presenter.getViewModel());

        // Display the result
        UserProfileViewModel viewModel = state.getUserProfileViewModel();
        System.out.println("User Profile Created:");
        System.out.println("Name: " + viewModel.getName());
        System.out.println("Email: " + viewModel.getEmail());
        //System.out.println("Courses: " + String.join(", ", viewModel.getCourses()));
        System.out.println("Creation Time: " + viewModel.getCreationTime());
    }
}
