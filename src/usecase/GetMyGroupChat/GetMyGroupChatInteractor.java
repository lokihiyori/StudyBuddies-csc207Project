package usecase.GetMyGroupChat;

import entity.Course;
import entity.GroupChat;
import entity.User;
import entity.Users;

import java.util.ArrayList;
import java.util.List;

public class GetMyGroupChatInteractor {
    final Users userDataAccessObject;
    final GetMyGroupChatOutPutBoundary getMyGroupChatOutPutBoundary;

    public GetMyGroupChatInteractor(Users userDataAccessObject, GetMyGroupChatOutPutBoundary getMyGroupChatOutPutBoundary){
        this.userDataAccessObject = userDataAccessObject;
        this.getMyGroupChatOutPutBoundary = getMyGroupChatOutPutBoundary;
    }

    public void execute(GetMyGroupChatInputData getMyGroupChatInputData) {
        Users users = userDataAccessObject.get(getMyGroupChatInputData.getUsers());
        List<Course> courses = users.getCourses();
        List<GroupChat> groupChatList = new ArrayList<>();
        for(Course course: courses){
            GroupChat groupChat = course.getGroupchat();
            groupChatList.add(groupChat);
        }
        GetMyGroupChatOutputData outputData = new GetMyGroupChatOutputData(users, groupChatList);
        GetMyGroupChatOutPutBoundary.prepareSuccessView(outputData);
    }
}
