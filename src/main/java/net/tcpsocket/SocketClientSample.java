package net.tcpsocket;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.time.LocalDateTime;

public class SocketClientSample {
    public static void main(String[] args) {
        SocketClientSample socketClientSample = new SocketClientSample();
        socketClientSample.sendSocketSample();
    }

    private void sendSocketSample() {
        for (int i = 0; i < 3; i++) {
            sendSocketData("I liked java at " + LocalDateTime.now());
        }

        sendSocketData("EXIT");
    }

    private void sendSocketData(String data) {
        System.out.println("Client: Connecting");
        try (Socket socket = new Socket("127.0.0.1", 9999)) {
            System.out.println("Client: Connect status=" + socket.isConnected());
            Thread.sleep(1000);

            OutputStream outputStream = socket.getOutputStream();
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);

            byte[] bytes = data.getBytes();
            bufferedOutputStream.write(bytes);
            System.out.println("Client: Sent data");

            bufferedOutputStream.close();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
