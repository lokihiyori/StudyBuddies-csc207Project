/*
package SocketIO;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GroupChatClient {
    private static Socket socket;
    private static JTextArea chatArea;
    private static JTextField inputField;
    private static String username;

    public static void main(String[] args) {
        // Set up the GUI
        JFrame frame = new JFrame("Group Chat");
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        frame.add(new JScrollPane(chatArea), BorderLayout.CENTER);

        inputField = new JTextField();

        // Set the preferred size with a larger height
        inputField.setPreferredSize(new Dimension(400, 50));
        // Add a titled border to the input field
        inputField.setBorder(BorderFactory.createTitledBorder("Type your message here"));

        frame.add(inputField, BorderLayout.SOUTH);

        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = inputField.getText();
                if (!message.trim().isEmpty()) {
                    inputField.setText("");
                    //appendMessage("Me: " + message);
                    socket.emit("message", username + ": " + message);
                }
            }
        });

        frame.setVisible(true);

        // Get the username
        username = JOptionPane.showInputDialog(frame, "Enter your username:");
        if (username == null || username.trim().isEmpty()) {
            username = "Anonymous";
        }

        // Connect to the server
        try {
            socket = IO.socket("http://localhost:3000");

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

    private static void appendMessage(String message) {
        chatArea.append(message + "\n");
        chatArea.setCaretPosition(chatArea.getDocument().getLength());
    }
}


 */



/*
// can be executed from CourseSearchView
package SocketIO;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GroupChatClient {
    private Socket socket;
    private JTextArea chatArea;
    private JTextField inputField;
    private String username;

    public void startChat(String frameTitle) {
        // Set up the GUI
        JFrame frame = new JFrame("Group Chat of " + frameTitle);
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        frame.add(new JScrollPane(chatArea), BorderLayout.CENTER);

        inputField = new JTextField();

        // Set the preferred size with a larger height
        inputField.setPreferredSize(new Dimension(400, 50));
        // Add a titled border to the input field
        inputField.setBorder(BorderFactory.createTitledBorder("Type your message here"));

        frame.add(inputField, BorderLayout.SOUTH);

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

        // Get the username
        username = JOptionPane.showInputDialog(frame, "Enter your username:");
        if (username == null || username.trim().isEmpty()) {
            username = "Anonymous";
        }

        // Connect to the server
        try {
            socket = IO.socket("http://localhost:3000");

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

    private void appendMessage(String message) {
        chatArea.append(message + "\n");
        chatArea.setCaretPosition(chatArea.getDocument().getLength());
    }
}


 */


package SocketIO;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GroupChatClient {
    private Socket socket;
    private JTextArea chatArea;
    private JTextField inputField;
    private JTextField courseSearchField;
    private String username;

    public void startChat(String frameTitle, int port) {
        // Set up the GUI
        JFrame frame = new JFrame("Group Chat of " + frameTitle);
        frame.setSize(400, 600); // Increased size to accommodate the new search field
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        frame.add(new JScrollPane(chatArea), BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());

        inputField = new JTextField();
        inputField.setPreferredSize(new Dimension(400, 50));
        inputField.setBorder(BorderFactory.createTitledBorder("Type your message here"));

        /*
        courseSearchField = new JTextField();
        courseSearchField.setPreferredSize(new Dimension(400, 30)); // Set width and height
        courseSearchField.setBorder(BorderFactory.createTitledBorder("Search for a course"));
         */
        //inputPanel.add(courseSearchField, BorderLayout.NORTH);
        inputPanel.add(inputField, BorderLayout.SOUTH);

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

        // Get the username
        username = JOptionPane.showInputDialog(frame, "Enter your username:");
        if (username == null || username.trim().isEmpty()) {
            username = "Anonymous";
        }

        // Connect to the server
        try {
            socket = IO.socket("http://localhost:" + port); // Use the specified port

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

    private void appendMessage(String message) {
        chatArea.append(message + "\n");
        chatArea.setCaretPosition(chatArea.getDocument().getLength());
    }
}
