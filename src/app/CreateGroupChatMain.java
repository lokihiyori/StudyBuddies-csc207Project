package app;

import data_access.CourseDataAccessObject;
import data_access.GroupChatDataAccessObject;
import entity.Course;
import entity.CourseFactory;
import entity.GroupChat;
import entity.GroupChatFactory;
import interface_adapter.CreateCourse.CreateCourseController;
import interface_adapter.CreateCourse.CreateCoursePresenter;
import interface_adapter.CreateCourse.CreateCourseState;
import interface_adapter.CreateCourse.CreateCourseViewModel;
import interface_adapter.CreateGroupChat.CreateGroupChatController;
import interface_adapter.CreateGroupChat.CreateGroupChatPresenter;
import interface_adapter.CreateGroupChat.CreateGroupChatState;
import interface_adapter.CreateGroupChat.CreateGroupChatViewModel;
import use_case.CreateCourse.CreateCourseInteractor;
import use_case.CreateGroupChat.CreateGroupChatDataAccessInterface;
import use_case.CreateGroupChat.CreateGroupChatInteractor;
import view.CreateGroupChatView;

import java.io.IOException;

public class CreateGroupChatMain {
    public static void main(String[] args) {
        // Setup
        String csvPath = "group chats.csv"; // Path to your CSV file
        GroupChatFactory groupChatFactory = new GroupChatFactory();

        // Initialize GroupChatDataAccessObject with required parameters
        GroupChatDataAccessObject dataAccessObject;
        try {
            dataAccessObject = new GroupChatDataAccessObject(csvPath, groupChatFactory);
        } catch (IOException e) {
            System.err.println("Failed to initialize GroupChatDataAccessObject: " + e.getMessage());
            return;
        }

        CreateGroupChatViewModel viewModel = new CreateGroupChatViewModel();
        CreateGroupChatPresenter presenter = new CreateGroupChatPresenter(viewModel);
        CreateGroupChatInteractor interactor = new CreateGroupChatInteractor(presenter, dataAccessObject, groupChatFactory);
        CreateGroupChatController controller = new CreateGroupChatController(interactor);
        // Execute Use Case
        controller.executeCreateGroupChat("CSC207");

        // Check Output
        CreateGroupChatState state = viewModel.getState();
        System.out.println("GroupChat Code: " + state.getCode());
        System.out.println("GroupChat Members: " + state.getUsersList());

        // Verify course added to DAO
        GroupChat createdGroupChat = dataAccessObject.getGroupChat("CSC207");
        if (createdGroupChat != null) {
            System.out.println("GroupChat successfully added to DAO:");
            System.out.println("GroupChat Code: " + createdGroupChat.getCode());
            System.out.println("GroupChat Members: " + createdGroupChat.getGroupMembers());
            System.out.println("GroupChat Message History: " + createdGroupChat.getMessageHistory());
        } else {
            System.out.println("Course was not added to DAO.");
        }
    }

}
