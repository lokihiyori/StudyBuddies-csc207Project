package app;

import data_access.FileUserDataAccessObject;
import entity.CommonUserFactory;
import entity.GroupChatFactory;
import interface_adapter.GroupChatViewModel;
import interface_adapter.logged_In.LoggedInViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.SignUp.SignUpViewModel;
import interface_adapter.ViewManagerModel;
import use_case.CreateGroupChat.CreateGroupChatDataAccessInterface;
import view.LoggedInView;
import view.LoginView;
import view.SignupView;
import view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static <GroupChatDataAccessObject> void main(String[] args) {
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
        LoggedInViewModel loggedInViewModel = new LoggedInViewModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        LoginViewModel loginViewModel = new LoginViewModel();
        SignUpViewModel signupViewModel = new SignUpViewModel();

        FileUserDataAccessObject userDataAccessObject;
        try {
            userDataAccessObject = new FileUserDataAccessObject("./user.csv", new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel, signupViewModel, userDataAccessObject);
        views.add(signupView, signupView.viewName);
        LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel, loggedInViewModel, userDataAccessObject, signupViewModel);
        views.add(loginView, loginView.viewName);

        // Initialize GroupChatDataAccessObject
        //GroupChatDataAccessObject groupChatDataAccessObject = new GroupChatDataAccessObject("path/to/csv");
        CreateGroupChatDataAccessInterface groupChatDataAccessObject;
        try {

            // Initialize GroupChatDataAccessObject with file path and GroupChatFactory
            groupChatDataAccessObject = new GroupChatDataAccessObject("./groupchats.csv", new GroupChatFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        GroupChatViewModel viewModel = new GroupChatViewModel();
        LoggedInView loggedInView= new LoggedInView(viewModel, groupChatDataAccessObject, viewModel, loggedInViewModel);
        views.add(loggedInView, loggedInView.viewName);

        viewManagerModel.setActiveView(signupView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }}