/*
package SocketIO;


import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DisconnectListener;

public class GroupChatServer {
    public static void main(String[] args) {
        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(3000);

        SocketIOServer server = new SocketIOServer(config);

        server.addConnectListener(new ConnectListener() {
            @Override
            public void onConnect(com.corundumstudio.socketio.SocketIOClient client) {
                System.out.println("Client connected: " + client.getSessionId());
            }
        });

        server.addDisconnectListener(new DisconnectListener() {
            @Override
            public void onDisconnect(com.corundumstudio.socketio.SocketIOClient client) {
                System.out.println("Client disconnected: " + client.getSessionId());
            }
        });

        server.addEventListener("message", String.class, new DataListener<String>() {
            @Override
            public void onData(com.corundumstudio.socketio.SocketIOClient client, String data, com.corundumstudio.socketio.AckRequest ackSender) {
                System.out.println("Received message: " + data);
                server.getBroadcastOperations().sendEvent("message", data);
                System.out.println("Broadcasted message: " + data);
            }
        });

        server.start();
        System.out.println("Server is running on port 3000");
        Runtime.getRuntime().addShutdownHook(new Thread(server::stop));
    }
}


 */








/*
// can be executed from CourseSearchView !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
package SocketIO;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;

public class GroupChatServer {
    private SocketIOServer server;

    public void startServer() {
        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(3000);

        server = new SocketIOServer(config);

        server.addConnectListener(new ConnectListener() {
            @Override
            public void onConnect(com.corundumstudio.socketio.SocketIOClient client) {
                System.out.println("Client connected: " + client.getSessionId());
            }
        });

        server.addDisconnectListener(new DisconnectListener() {
            @Override
            public void onDisconnect(com.corundumstudio.socketio.SocketIOClient client) {
                System.out.println("Client disconnected: " + client.getSessionId());
            }
        });

        server.addEventListener("message", String.class, new DataListener<String>() {
            @Override
            public void onData(com.corundumstudio.socketio.SocketIOClient client, String data, com.corundumstudio.socketio.AckRequest ackSender) {
                System.out.println("Received message: " + data);
                server.getBroadcastOperations().sendEvent("message", data);
                System.out.println("Broadcasted message: " + data);
            }
        });

        server.start();
        System.out.println("Server is running on port 3000");

        // Add shutdown hook to stop the server gracefully
        Runtime.getRuntime().addShutdownHook(new Thread(server::stop));
    }
}

 */

package SocketIO;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;

public class GroupChatServer {
    private SocketIOServer server;

    public void startServer(int port) {
        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(port); // Use the specified port

        server = new SocketIOServer(config);

        server.addConnectListener(new ConnectListener() {
            @Override
            public void onConnect(com.corundumstudio.socketio.SocketIOClient client) {
                System.out.println("Client connected: " + client.getSessionId());
            }
        });

        server.addDisconnectListener(new DisconnectListener() {
            @Override
            public void onDisconnect(com.corundumstudio.socketio.SocketIOClient client) {
                System.out.println("Client disconnected: " + client.getSessionId());
            }
        });

        server.addEventListener("message", String.class, new DataListener<String>() {
            @Override
            public void onData(com.corundumstudio.socketio.SocketIOClient client, String data, com.corundumstudio.socketio.AckRequest ackSender) {
                System.out.println("Received message: " + data);
                server.getBroadcastOperations().sendEvent("message", data);
                System.out.println("Broadcasted message: " + data);
            }
        });

        server.start();
        System.out.println("Server is running on port " + port);
        Runtime.getRuntime().addShutdownHook(new Thread(server::stop));
    }
}
