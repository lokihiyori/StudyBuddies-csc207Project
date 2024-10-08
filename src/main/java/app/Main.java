package app;

import data_access.FileEventDataAccessObject;
import data_access.FileUserDataAccessObject;
import entity.CommonCalendarEventFactory;
import entity.CommonUserFactory;
import interface_adapter.CreateEvent.CreateEventViewModel;
import interface_adapter.GoToCourse.CourseViewModel;
import interface_adapter.GroupChatViewModel;
import interface_adapter.LoginSignup.LoginSignupViewModel;
import interface_adapter.logged_In.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.SignUp.SignUpViewModel;
import interface_adapter.ViewManagerModel;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * The main class to initialize and run the application.
 */
public class Main {

    /**
     * The main method to initialize and set up the main application window,
     * views, controllers, and models.
     *
     * @param args the command line arguments
     * @throws IOException if there is an error accessing the data files
     */
    public static void main(String[] args) throws IOException {
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        // The main application window.
        JFrame application = new JFrame("Sign Up");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        CourseViewModel courseViewModel = new CourseViewModel();
        LoginViewModel loginViewModel = new LoginViewModel();
        SignUpViewModel signupViewModel = new SignUpViewModel();
        LoginSignupViewModel loginSignupViewModel = new LoginSignupViewModel();
        GroupChatViewModel viewModel = new GroupChatViewModel();
        CreateEventViewModel createEventViewModel = new CreateEventViewModel();

        FileUserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new FileUserDataAccessObject("./user.csv", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        FileEventDataAccessObject eventDataAccessObject;
        try {
            eventDataAccessObject = new FileEventDataAccessObject("./events.csv", new CommonUserFactory(), new CommonCalendarEventFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject, loginSignupViewModel);
        views.add(signupView, signupView.viewName);
        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, userDataAccessObject, signupViewModel, loginSignupViewModel);
        views.add(loginView, loginView.viewName);
        LoggedInView loggedInView = LoggedInUseCaseFactory.create(viewManagerModel, loggedInViewModel, courseViewModel, createEventViewModel,userDataAccessObject);
        views.add(loggedInView, loggedInView.viewName);
        CourseView courseView = GoToCourseUseCaseFactory.create(viewManagerModel, courseViewModel, loggedInViewModel, createEventViewModel, userDataAccessObject, eventDataAccessObject);
        views.add(courseView, courseView.viewName);
        CreateEventView createEventView = CreateEventUseCaseFactory.create(viewManagerModel, createEventViewModel, courseViewModel, eventDataAccessObject, userDataAccessObject, application);
        views.add(createEventView, createEventView.viewName);

        viewManagerModel.setActiveView(signupView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}