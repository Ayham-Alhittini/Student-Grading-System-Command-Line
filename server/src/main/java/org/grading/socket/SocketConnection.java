package org.grading.socket;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketConnection {

    private final Socket clientSocket;
    private final ObjectOutputStream sendObject;
    private final ObjectInputStream receiveObject;

    public SocketConnection() {

        try {
            int PORT = 8000;
            try (ServerSocket serverSocket = new ServerSocket(PORT)) {

                System.out.println("Server is running....");
                clientSocket = serverSocket.accept();

                sendObject = new ObjectOutputStream(clientSocket.getOutputStream());
                receiveObject = new ObjectInputStream(clientSocket.getInputStream());

            }

        } catch (Exception e) {
            System.out.println("Exception while trying to connect.");
            throw new RuntimeException(e);
        }
    }

    public void sendObjectToClient(Object object) {
        try {
            sendObject.writeObject(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Object receiveObjectFromClient() {
        try {
            return receiveObject.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void closeConnection() {
        try {

            sendObject.close();
            receiveObject.close();

            clientSocket.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
