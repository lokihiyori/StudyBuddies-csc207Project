package use_case.EnrollInCourse;

import data_access.GroupChatDataAccessObject;
import entity.GroupChat;
import interface_adapter.GroupChatViewModel;
import interface_adapter.logged_In.LoggedInViewModel;

public class EnrollInCourseInteractor implements EnrollInCourseInputBoundary {
    private final GroupChatDataAccessObject groupChatDAO;
    private final GroupChatViewModel groupChatViewModel;


    public EnrollInCourseInteractor(GroupChatDataAccessObject groupChatDAO, GroupChatViewModel groupChatViewModel) {
        this.groupChatDAO = groupChatDAO;
        this.groupChatViewModel = groupChatViewModel;
    }


    public boolean enrollUserInCourse(String courseCode) {
        // Check if the course exists in the database
        if (!groupChatViewModel.isCourseInDatabase(courseCode)) {
            groupChatViewModel.showError("Course does not exist");
            return false;
        }

        // Check if a group chat already exists for this course
        if (groupChatDAO.existsByCode(courseCode)) {
            groupChatViewModel.showError("Group chat already exists for this course");
            return false;
        }

        // Create and save the new group chat
        GroupChat newGroupChat = new GroupChat(courseCode);
        groupChatDAO.saveGroupChat(newGroupChat);

        // Update the ViewModel
        groupChatViewModel.createGroupChat(courseCode);
        return true;
    }

    @Override
    public void enrollInCourse(String courseCode, String userEmail) {

    }
}