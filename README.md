# StudyBuddies
This software would allow students to create an account with a profile that displays all their enrolled courses and their emails. Once users have logged in, they will join a group for each of their courses. Users can search for other students in their courses, using other users' profiles to see which courses they are enrolled in and their emails. Each course will have a group chat that will include all the students enrolled in that course.

## Whole Project Walk Through (Logics behind)
1. When you launch the app you can sign in and log in.
2. After logging in, on the main page you can choose to go to search courses or go to groupchats you joined.
3. If you go to search courses, you can search courses, if a course exists you are asked to join the related course groupchat or not join,if you join the grouchat you can either go to the groupchat that you join or search other courses. If the course is not existed you can choose to create one.
4. On the main page if you choose the go the groupchat open you will see a list of groupchats that you joined, you click into one of them and talk to other group members, you can see other group members' profiles by clicking them.
5. Users can then leave groupchat after joining.

## Phase One Progress and How to Run
1. **Clone the repository**: https://github.com/lokihiyori/StudyBuddies-csc207Project
2. In Phase One we Implement UIs for the first three parts of the logics listed above, so **to run the program you need go to src/app/main and run it**, and then you will see the sign up page, fill in all the info and click sign up, and then you will be directed to the log in page, put in you info again and log in.
3. You will see two options on the main page, you can either search course or go to your courses. 

   Since in our case, if there is a course in the course list, there is always a group chat
created which is associated with this course. Because of the one-to-one mapping,
searching course is equivalent to searching group chat.
**There are three courses initialized to be stored in the course list and their group chats
has been created**:
 Course name: SOFTWARE DESIGN, Course code: CSC207
 Course name: CALCULUS I, Course code MAT141
 Course name: PHYSICS I, Course code PHY131
These three courses/group chats can be found either by providing course name or
course code. For example, if searching query is CSC207, the result would be “Group
chat for CSC207 exits. Do you want to join?” If searching query is CSC209, the result
would be “No group chat for CSC209 exits. Do you want to create one?”
The searching is made to be case insensitive for better experience of user. For
instance, query like “software design” or “Software Design” can both find the group
chat of CSC207.

We have finished implementing UIs for go to your courses so it temperarily displays a default lists.

4. You can manully create more courses and user profiles in src/app/CreateCourseMain and src/app/UserProfileMain.
