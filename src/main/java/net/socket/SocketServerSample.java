package net.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerSample {
    public static void main(String[] args) {
        SocketServerSample socketServerSample = new SocketServerSample();
        socketServerSample.startServer();
    }

    private void startServer() {
        try(ServerSocket serverSocket = new ServerSocket(9999)) {
            Socket client;
            while (true) {
                System.out.println("Server: Waiting for request.");

                client = serverSocket.accept();
                System.out.println("Server: Accepted.");

                InputStream inputStream = client.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                String data;
                StringBuilder receivedData = new StringBuilder();
                while ((data = bufferedReader.readLine()) != null) {
                    receivedData.append(data);
                }

                System.out.println("ReceivedData: " + receivedData);
                bufferedReader.close();
                inputStream.close();
                client.close();
                if ("EXIT".contentEquals(receivedData)) {
                    System.out.println("Stop SocketServer");
                    break;
                }

                System.out.println("=====================");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
