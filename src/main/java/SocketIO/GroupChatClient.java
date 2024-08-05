//package SocketIO;
//
//import io.socket.client.IO;
//import io.socket.client.Socket;
//import io.socket.emitter.Emitter;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
///**
// * The GroupChatClient class provides a simple GUI-based group chat client using Socket.IO for communication.
// */
//public class GroupChatClient {
//    private Socket socket;
//    private JTextArea chatArea;
//    private JTextField inputField;
//    private JTextField courseSearchField;
//    private String username;
//
//    /**
//     * Starts the chat client with the specified frame title, host, and port.
//     *
//     * @param frameTitle the title of the chat frame
//     * @param host the host address of the chat server
//     * @param port the port number of the chat server
//     */
//    public void startChat(String frameTitle, String host, int port) {
//        // Set up the GUI
//        JFrame frame = new JFrame("Group Chat of " + frameTitle);
//        frame.setSize(400, 600);
//
//        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//
//        frame.setLayout(new BorderLayout());
//
//        chatArea = new JTextArea();
//        chatArea.setEditable(false);
//        frame.add(new JScrollPane(chatArea), BorderLayout.CENTER);
//
//        JPanel inputPanel = new JPanel();
//        inputPanel.setLayout(new BorderLayout());
//
//        inputField = new JTextField();
//        inputField.setPreferredSize(new Dimension(400, 50));
//        inputField.setBorder(BorderFactory.createTitledBorder("Type your message here"));
//
//        inputPanel.add(inputField, BorderLayout.SOUTH);
//
//        frame.add(inputPanel, BorderLayout.SOUTH);
//
//        inputField.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String message = inputField.getText();
//                if (!message.trim().isEmpty()) {
//                    inputField.setText("");
//                    socket.emit("message", username + ": " + message);
//                }
//            }
//        });
//
//        frame.setVisible(true);
//
//        // Get the username
//        username = JOptionPane.showInputDialog(frame, "Enter your username:");
//        if (username == null || username.trim().isEmpty()) {
//            username = "Anonymous";
//        }
//
//        //add to groupchat member list
//        GroupChatMember groupChatMember = new GroupChatMember();
//        groupChatMember.addMember(frameTitle, username);
//
//        // Connect to the server
//        try {
//            socket = IO.socket("http://" + host + ":" + port); // Use the specified host and port
//
//            socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
//                @Override
//                public void call(Object... args) {
//                    appendMessage("Connected to the server");
//                }
//            });
//
//            socket.on("message", new Emitter.Listener() {
//                @Override
//                public void call(Object... args) {
//                    String message = (String) args[0];
//                    appendMessage(message);
//                }
//            });
//
//            socket.on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
//                @Override
//                public void call(Object... args) {
//                    appendMessage("Disconnected from the server");
//                }
//            });
//
//            socket.connect();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * Appends a message to the chat area.
//     *
//     * @param message the message to append
//     */
//    private void appendMessage(String message) {
//        chatArea.append(message + "\n");
//        chatArea.setCaretPosition(chatArea.getDocument().getLength());
//    }
//}


package SocketIO;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * The GroupChatClient class provides a simple GUI-based group chat client using Socket.IO for communication.
 */
public class GroupChatClient {
    private Socket socket;
    private JTextArea chatArea;
    private JTextField inputField;
    private JTextField courseSearchField;
    private String username;
    private String courseCode;

    /**
     * Starts the chat client with the specified frame title, host, and port.
     *
     * @param frameTitle the title of the chat frame
     * @param host the host address of the chat server
     * @param port the port number of the chat server
     */
    public void startChat(String frameTitle, String host, int port) {
        courseCode = frameTitle;

        // Set up the GUI
        JFrame frame = new JFrame("Group Chat of " + frameTitle);
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        frame.add(new JScrollPane(chatArea), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());

        inputField = new JTextField();
        inputField.setPreferredSize(new Dimension(400, 50));
        inputField.setBorder(BorderFactory.createTitledBorder("Type your message here"));

        inputPanel.add(inputField, BorderLayout.SOUTH);

        // Add the "Group Members" button
        JButton groupMembersButton = new JButton("Group Members");
        inputPanel.add(groupMembersButton, BorderLayout.NORTH);

        groupMembersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showGroupMembers();
            }
        });

        frame.add(inputPanel, BorderLayout.SOUTH);

        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = inputField.getText();
                if (!message.trim().isEmpty()) {
                    inputField.setText("");
                    socket.emit("message", username + ": " + message);
                }
            }
        });

        frame.setVisible(true);

        // Get the chat handle
        username = JOptionPane.showInputDialog(frame, "Enter your username:");
        if (username == null || username.trim().isEmpty()) {
            username = "Anonymous";
        }

        //add to groupchat member list
        GroupChatMember groupChatMember = new GroupChatMember();
        groupChatMember.addMember(courseCode, username);


        // Connect to the server
        try {
            socket = IO.socket("http://" + host + ":" + port); // Use the specified host and port

            socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    appendMessage("Connected to the server");
                }
            });

            socket.on("message", new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    String message = (String) args[0];
                    appendMessage(message);
                }
            });

            socket.on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
                @Override
                public void call(Object... args) {
                    appendMessage("Disconnected from the server");
                }
            });

            socket.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows a window with the list of group members for the specified course code.
     */
    private void showGroupMembers() {
        GroupChatMember groupChatMember = new GroupChatMember();
        List<String> members = groupChatMember.getMembersByCourseCode(courseCode);

        StringBuilder membersList = new StringBuilder();
        for (String member : members) {
            membersList.append(member).append("\n");
        }

        JTextArea membersTextArea = new JTextArea(membersList.toString());
        membersTextArea.setEditable(false);

        JFrame membersFrame = new JFrame("Group Members for " + courseCode);
        membersFrame.setSize(300, 400);
        membersFrame.add(new JScrollPane(membersTextArea));
        membersFrame.setVisible(true);
    }

    /**
     * Appends a message to the chat area.
     *
     * @param message the message to append
     */
    private void appendMessage(String message) {
        chatArea.append(message + "\n");
        chatArea.setCaretPosition(chatArea.getDocument().getLength());
    }

    public static void main(String[] args) {
        GroupChatClient client = new GroupChatClient();
        client.startChat("Example Chat", "localhost", 4000);
    }
}
