package org.grading.socket;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketConnection {

    private final ObjectOutputStream sendObject;
    private final ObjectInputStream receiveObject;

    public SocketConnection() {

        try {
            int PORT = 8000;
            Socket serverSocket = new Socket("localhost", PORT);

            sendObject = new ObjectOutputStream(serverSocket.getOutputStream());
            receiveObject = new ObjectInputStream(serverSocket.getInputStream());

        } catch (Exception e) {
            System.out.println("Exception while trying to connect.");
            throw new RuntimeException(e);
        }
    }

    public void sendObjectToServer(Object object) {
        try {
            sendObject.writeObject(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Object receiveObjectFromServer() {
        try {
            return receiveObject.readObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
